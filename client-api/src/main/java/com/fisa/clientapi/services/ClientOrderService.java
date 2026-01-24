package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.BusinessNotFoundException;
import com.fisa.clientapi.exceptions.OrderItemsListCannotBeNullException;
import com.fisa.clientapi.exceptions.OrderEntriesCannotBeEmptyOrNullException;
import com.fisa.clientapi.exceptions.ClientOrderNotFoundException;
import com.fisa.clientapi.exceptions.ProductNotFoundException;
import com.fisa.clientapi.models.Business;
import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.CreateClientOrderRequest;
import com.fisa.clientapi.models.ProducerOrder;
import com.fisa.clientapi.models.ClientOrderDetails;
import com.fisa.clientapi.models.ClientOrderItem;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.models.UpdateClientOrderRequest;
import com.fisa.clientapi.models.enums.OrderStatus;
import com.fisa.clientapi.repositories.BusinessRepository;
import com.fisa.clientapi.repositories.ClientOrderRepository;
import com.fisa.clientapi.repositories.ProducerOrderRepository;
import com.fisa.clientapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientOrderService {

  private final ProducerOrderRepository producerOrderRepository;
  private final ClientOrderRepository clientOrderRepository;
  private final BusinessRepository businessRepository;
  private final ProductRepository productRepository;

  public ClientOrder createNewOrder(CreateClientOrderRequest createClientOrderRequest) {
    if (createClientOrderRequest == null) {
      throw new OrderItemsListCannotBeNullException();
    }

    if (createClientOrderRequest.getOrderItems() == null || createClientOrderRequest.getOrderItems().isEmpty()) {
      throw new OrderEntriesCannotBeEmptyOrNullException();
    }

    validateOrderReferences(createClientOrderRequest.getOrderItems());

    final ClientOrder newClientOrder = ClientOrder.builder()
            .clientOrderId(UUID.randomUUID().toString())
            .clientId(createClientOrderRequest.getClientId())
            .orderDate(LocalDateTime.now(ZoneOffset.UTC))
            .orderStatus(OrderStatus.REGISTERED)
            .orderItems(createClientOrderRequest.getOrderItems())
            .deliveryAddress(createClientOrderRequest.getDeliveryAddress())
            .totalPrice(computeTotalPrice(createClientOrderRequest.getOrderItems()))
            .email(createClientOrderRequest.getEmail())
            .phone(createClientOrderRequest.getPhone())
            .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
            .build();

    saveProducersOrders(newClientOrder);

    return clientOrderRepository.save(newClientOrder);
  }

  private Double computeTotalPrice(List<ClientOrderItem> clientOrderItems) {
    if (clientOrderItems == null || clientOrderItems.isEmpty()) {
      return 0.0;
    }

    final double total = clientOrderItems.stream()
            .mapToDouble(entry -> entry.getUnitPrice() * entry.getQuantity())
            .sum();

    return BigDecimal.valueOf(total)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
  }

  private void saveProducersOrders(ClientOrder newClientOrder) {
    final Map<String, List<ClientOrderItem>> clientOrdersByProducer = newClientOrder.getOrderItems()
            .stream()
            .collect(Collectors.groupingBy(ClientOrderItem::getBusinessId));

    List<ProducerOrder> ordersToSave = clientOrdersByProducer.entrySet().stream()
            .map(entry ->
                    createProducerOrder(newClientOrder, entry.getKey(), entry.getValue())
            )
            .toList();

    producerOrderRepository.saveAll(ordersToSave);
  }

  private void validateOrderReferences(List<ClientOrderItem> clientOrderItems) {
    Set<String> productIds = clientOrderItems.stream()
            .map(ClientOrderItem::getProductId)
            .collect(Collectors.toSet());

    Set<String> businessIds = clientOrderItems.stream()
            .map(ClientOrderItem::getBusinessId)
            .collect(Collectors.toSet());

    Set<String> existingBusinessIds = businessRepository.findAllByBusinessIdIn(businessIds).stream()
            .map(Business::getBusinessId)
            .collect(Collectors.toSet());

    Set<String> missingBusinessIds = new HashSet<>(businessIds);
    missingBusinessIds.removeAll(existingBusinessIds);

    if (!missingBusinessIds.isEmpty()) {
      throw new BusinessNotFoundException(
              "Les businesses suivants n'existent pas : " + missingBusinessIds
      );
    }

    Set<String> existingProductIds = productRepository.findAllByProductIdIn(productIds).stream()
            .map(Product::getProductId)
            .collect(Collectors.toSet());

    Set<String> missingProductIds = new HashSet<>(productIds);
    missingProductIds.removeAll(existingProductIds);

    if (!missingProductIds.isEmpty()) {
      throw new ProductNotFoundException(
              "Les produits suivants n'existent pas : " + missingProductIds
      );
    }
  }

  private ProducerOrder createProducerOrder(ClientOrder clientOrder, String businessId, List<ClientOrderItem> clientOrderItems) {
    return ProducerOrder.builder()
            .producerOrderId(UUID.randomUUID().toString())
            .clientOrderId(clientOrder.getClientOrderId())
            .businessId(businessId)
            .deliveryAddress(clientOrder.getDeliveryAddress())
            .email(clientOrder.getEmail())
            .phone(clientOrder.getPhone())
            .clientOrderItems(clientOrderItems)
            .orderStatus(OrderStatus.REGISTERED)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }

  public ClientOrderDetails getOrderDetails(String clientOrderId) {
    final ClientOrder existingClientOrder = clientOrderRepository.findByClientOrderId(clientOrderId)
            .orElseThrow(ClientOrderNotFoundException::new);

    if (existingClientOrder.getOrderItems().isEmpty()) {
      throw new OrderEntriesCannotBeEmptyOrNullException();
    }

    final Map<String, Product> productMap = productRepository
            .findAllByProductIdIn(extractProductIds(existingClientOrder))
            .stream()
            .collect(Collectors.toMap(Product::getProductId, p -> p));

    final Map<String, Business> businessMap = businessRepository
            .findAllByBusinessIdIn(extractBusinessIds(existingClientOrder))
            .stream()
            .collect(Collectors.toMap(Business::getBusinessId, b -> b));

    validateAllProductsExist(existingClientOrder, productMap);
    validateAllBusinessesExist(existingClientOrder, businessMap);

    List<ProducerOrder> existingProducerOrders = producerOrderRepository.findByClientOrderId(clientOrderId);
    if (existingProducerOrders.isEmpty()) {
      throw new OrderEntriesCannotBeEmptyOrNullException();
    }

    Map<String, List<ProducerOrder>> producerOrders =
            existingProducerOrders.stream()
                    .collect(Collectors.groupingBy(
                            po -> businessMap.get(po.getBusinessId()).getName(),
                            LinkedHashMap::new,
                            Collectors.toList()
                    ));

    return ClientOrderDetails.builder()
            .clientOrderId(existingClientOrder.getClientOrderId())
            .clientId(existingClientOrder.getClientOrderId())
            .deliveryAddress(existingClientOrder.getDeliveryAddress())
            .email(existingClientOrder.getEmail())
            .phone(existingClientOrder.getPhone())
            .producerOrders(producerOrders)
            .orderDate(existingClientOrder.getOrderDate())
            .orderStatus(existingClientOrder.getOrderStatus())
            .totalPrice(existingClientOrder.getTotalPrice())
            .build();
  }

  private Set<String> extractProductIds(ClientOrder clientOrder) {
    return clientOrder.getOrderItems().stream()
            .map(ClientOrderItem::getProductId)
            .collect(Collectors.toSet());
  }

  private Set<String> extractBusinessIds(ClientOrder clientOrder) {
    return clientOrder.getOrderItems().stream()
            .map(ClientOrderItem::getBusinessId)
            .collect(Collectors.toSet());
  }

  private void validateAllProductsExist(ClientOrder clientOrder, Map<String, Product> productMap) {
    Set<String> missingProducts = clientOrder.getOrderItems().stream()
            .map(ClientOrderItem::getProductId)
            .filter(id -> !productMap.containsKey(id))
            .collect(Collectors.toSet());

    if (!missingProducts.isEmpty()) {
      throw new ProductNotFoundException("Produits introuvables : " + missingProducts);
    }
  }

  private void validateAllBusinessesExist(ClientOrder clientOrder, Map<String, Business> businessMap) {
    Set<String> missingBusinesses = clientOrder.getOrderItems().stream()
            .map(ClientOrderItem::getBusinessId)
            .filter(id -> !businessMap.containsKey(id))
            .collect(Collectors.toSet());

    if (!missingBusinesses.isEmpty()) {
      throw new BusinessNotFoundException("Producteurs introuvables : " + missingBusinesses);
    }
  }

  public ClientOrderDetails updateOrder(UpdateClientOrderRequest updateClientOrderRequest) {
    final ClientOrder existingClientOrder = clientOrderRepository
            .findByClientOrderId(updateClientOrderRequest.getClientOrderId())
            .orElseThrow(ClientOrderNotFoundException::new);

    if (existingClientOrder.getOrderItems() == null || existingClientOrder.getOrderItems().isEmpty()) {
      throw new OrderEntriesCannotBeEmptyOrNullException();
    }

    existingClientOrder.setEmail(updateClientOrderRequest.getEmail());
    existingClientOrder.setPhone(updateClientOrderRequest.getPhone());
    if (updateClientOrderRequest.getDeliveryAddress() != null) {
      existingClientOrder.setDeliveryAddress(updateClientOrderRequest.getDeliveryAddress());
    }


    List<ClientOrderItem> allItems = updateClientOrderRequest.getProducerOrders().values().stream()
            .flatMap(List::stream)
            .flatMap(po -> po.getClientOrderItems().stream())
            .toList();

    existingClientOrder.setOrderItems(allItems);
    existingClientOrder.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

    updateProducerOrders(updateClientOrderRequest.getClientOrderId(), updateClientOrderRequest.getProducerOrders());

    ClientOrder updatedClientOrder = clientOrderRepository.save(existingClientOrder);

    return getOrderDetails(updatedClientOrder.getClientOrderId());
  }

  private void updateProducerOrders(String clientOrderId, Map<String, List<ProducerOrder>> producerOrdersMap) {
    List<String> producerOrderIdsInRequest = producerOrdersMap.values().stream()
            .flatMap(List::stream)
            .map(ProducerOrder::getProducerOrderId)
            .toList();

    List<ProducerOrder> allExistingProducerOrders = producerOrderRepository
            .findByClientOrderId(clientOrderId);

    Map<String, ProducerOrder> existingMap = allExistingProducerOrders.stream()
            .collect(Collectors.toMap(ProducerOrder::getProducerOrderId, po -> po));

    List<String> updatedProducerOrderToDelete = new ArrayList<>();
    List<ProducerOrder> toUpdate = producerOrdersMap.values().stream()
            .flatMap(List::stream)
            .map(producerOrder -> {
              ProducerOrder existing = existingMap.get(producerOrder.getProducerOrderId());
              if (existing != null) {
                updatedProducerOrderToDelete.add(existing.getProducerOrderId());
                return ProducerOrder.builder()
                        .producerOrderId(existing.getProducerOrderId())
                        .clientOrderId(existing.getClientOrderId())
                        .businessId(existing.getBusinessId())
                        .deliveryAddress(existing.getDeliveryAddress())
                        .email(existing.getEmail())
                        .phone(existing.getPhone())
                        .clientOrderItems(producerOrder.getClientOrderItems())
                        .orderStatus(existing.getOrderStatus())
                        .createdAt(existing.getCreatedAt())
                        .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
                        .build();
              }
              return null;
            })
            .filter(Objects::nonNull)
            .toList();

    List<String> producerOrderIdsToDelete = allExistingProducerOrders.stream()
            .filter(existing -> !producerOrderIdsInRequest.contains(existing.getProducerOrderId()))
            .map(ProducerOrder::getProducerOrderId)
            .toList();

    if (!toUpdate.isEmpty()) {
      producerOrderRepository.deleteAllByProducerOrderIdIn(updatedProducerOrderToDelete);
      producerOrderRepository.saveAll(toUpdate);
    }

    if (!producerOrderIdsToDelete.isEmpty()) {
      producerOrderRepository.deleteAllByProducerOrderIdIn(producerOrderIdsToDelete);
    }
  }

}
