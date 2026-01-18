package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.BusinessNotFoundException;
import com.fisa.clientapi.exceptions.ClientOrderCannotBeNullException;
import com.fisa.clientapi.exceptions.OrderEntriesCannotBeEmptyOrNullException;
import com.fisa.clientapi.exceptions.ProductNotFoundException;
import com.fisa.clientapi.models.Business;
import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.Order;
import com.fisa.clientapi.models.OrderEntry;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.models.enums.OrderStatus;
import com.fisa.clientapi.repositories.BusinessRepository;
import com.fisa.clientapi.repositories.ClientOrderRepository;
import com.fisa.clientapi.repositories.OrderRepository;
import com.fisa.clientapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ClientOrderRepository clientOrderRepository;
  private final BusinessRepository businessRepository;
  private final ProductRepository productRepository;

  public ClientOrder createNewOrder(ClientOrder newClientOrder) {
    if (newClientOrder == null) {
      throw new ClientOrderCannotBeNullException();
    }

    if (newClientOrder.getOrderEntries() == null || newClientOrder.getOrderEntries().isEmpty()) {
      throw new OrderEntriesCannotBeEmptyOrNullException();
    }

    validateOrderReferences(newClientOrder.getOrderEntries());

    newClientOrder.setClientOrderId(UUID.randomUUID().toString());
    newClientOrder.setOrderDate(LocalDateTime.now());
    newClientOrder.setOrderStatus(OrderStatus.REGISTERED);
    newClientOrder.setTotalPrice(computeTotalPrice(newClientOrder.getOrderEntries()));

    saveProducersOrders(newClientOrder);

    return clientOrderRepository.save(newClientOrder);
  }

  private Double computeTotalPrice(List<OrderEntry> orderEntries) {
    if (orderEntries == null || orderEntries.isEmpty()) {
      return 0.0;
    }

    final double total = orderEntries.stream()
            .mapToDouble(entry -> entry.getUnitPrice() * entry.getQuantity())
            .sum();

    return BigDecimal.valueOf(total)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
  }

  private void saveProducersOrders(ClientOrder newClientOrder) {
    final Map<String, List<OrderEntry>> ordersByProducer = newClientOrder.getOrderEntries()
            .stream()
            .collect(Collectors.groupingBy(OrderEntry::getBusinessId));


    List<Order> ordersToSave = ordersByProducer.entrySet().stream()
            .map(entry ->
                    createProducerOrder(newClientOrder, entry.getKey(), entry.getValue()))
            .toList();

    orderRepository.saveAll(ordersToSave);
  }

  private void validateOrderReferences(List<OrderEntry> orderEntries) {
    Set<String> productIds = orderEntries.stream()
            .map(OrderEntry::getProductId)
            .collect(Collectors.toSet());

    Set<String> businessIds = orderEntries.stream()
            .map(OrderEntry::getBusinessId)
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

  private Order createProducerOrder(ClientOrder clientOrder, String businessId, List<OrderEntry> orderEntries) {
    return Order.builder()
            .orderId(UUID.randomUUID().toString())
            .clientOrderId(clientOrder.getClientOrderId())
            .clientId(clientOrder.getClientId())
            .businessId(businessId)
            .deliveryAddress(clientOrder.getDeliveryAddress())
            .email(clientOrder.getEmail())
            .phone(clientOrder.getPhone())
            .orderEntries(orderEntries)
            .orderStatus(OrderStatus.REGISTERED)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }


}
