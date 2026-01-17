package com.fisa.clientapi.services;

import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.Order;
import com.fisa.clientapi.models.OrderEntry;
import com.fisa.clientapi.models.enums.OrderStatus;
import com.fisa.clientapi.repositories.ClientOrderRepository;
import com.fisa.clientapi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    if (newClientOrder.getOrderEntries() == null || newClientOrder.getOrderEntries().isEmpty()) {
      System.out.println("y a un problème chef");
      return null;
    }

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

    Double total = orderEntries.stream()
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
              .orderEntries(value)
              .orderStatus(OrderStatus.REGISTERED)
              .createdAt(LocalDateTime.now())
              .updatedAt(LocalDateTime.now())
              .build();

      ordersToSave.add(newOrder);
    });

    orderRepository.saveAll(ordersToSave);
  }

}
