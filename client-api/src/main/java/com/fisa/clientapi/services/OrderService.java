package com.fisa.clientapi.services;

import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.Order;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.models.enums.OrderStatus;
import com.fisa.clientapi.repositories.ClientOrderRepository;
import com.fisa.clientapi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ClientOrderRepository clientOrderRepository;

  public ClientOrder createNewOrder(ClientOrder newClientOrder) {
    if (newClientOrder == null) {
      System.out.println("y a un problème chef");
      return null;
    }

    if (newClientOrder.getProducts() == null || newClientOrder.getProducts().isEmpty()) {
      System.out.println("y a un problème chef");
      return null;
    }

    newClientOrder.setClientOrderId(UUID.randomUUID().toString());

    saveProducersOrders(newClientOrder);

    return clientOrderRepository.save(newClientOrder);
  }

  private void saveProducersOrders(ClientOrder newClientOrder) {
    final Map<String, List<Product>> ordersByProducer = newClientOrder.getProducts()
            .stream()
            .collect(Collectors.groupingBy(Product::getBusinessId));

    final List<Order> ordersToSave = new ArrayList<>();
    ordersByProducer.forEach((key, value) -> {
      final Order newOrder = Order.builder()
              .orderId(UUID.randomUUID().toString())
              .clientOrderId(newClientOrder.getClientOrderId())
              .clientId(newClientOrder.getClientId())
              .businessId(key)
              .deliveryAddress(newClientOrder.getDeliveryAddress())
              .email(newClientOrder.getEmail())
              .phone(newClientOrder.getPhone())
              .products(value)
              .orderStatus(OrderStatus.REGISTERED)
              .createdAt(LocalDateTime.now())
              .updatedAt(LocalDateTime.now())
              .build();

      ordersToSave.add(newOrder);
    });

    orderRepository.saveAll(ordersToSave);
  }

}
