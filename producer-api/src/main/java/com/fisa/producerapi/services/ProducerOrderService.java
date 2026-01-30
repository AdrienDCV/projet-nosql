package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.businesses.BusinessNotFoundException;
import com.fisa.producerapi.exceptions.producerOrders.ProducerOrderNotFoundException;
import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.models.ClientOrderItem;
import com.fisa.producerapi.models.ProducerOrder;
import com.fisa.producerapi.models.ProducerOrderHistory;
import com.fisa.producerapi.models.ProducerOrderHistoryRecord;
import com.fisa.producerapi.models.enums.OrderStatus;
import com.fisa.producerapi.repositories.BusinessRepository;
import com.fisa.producerapi.repositories.ProducerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProducerOrderService {

  private final ProducerOrderRepository producerOrderRepository;
  private final BusinessRepository businessRepository;

  private final MongoTemplate mongoTemplate;

  public List<ProducerOrder> getProducerOrders(String businessId) {
    final List<ProducerOrder> existingProducerOrders = producerOrderRepository.findAllByBusinessId(businessId);

    return existingProducerOrders.isEmpty() ? Collections.emptyList() : existingProducerOrders;
  }

  public ProducerOrder updateProducerOrderStatus(String producerOrderId, OrderStatus newOrderStatus) {
    final ProducerOrder existingProducerOrder = producerOrderRepository.findByProducerOrderId(producerOrderId)
            .orElseThrow(ProducerOrderNotFoundException::new);

    final Query query = Query.query(
            Criteria.where("businessId").is(existingProducerOrder.getBusinessId())
                    .and("producerOrderId").is(producerOrderId)
    );

    Update update = new Update().set("orderStatus", newOrderStatus);

    return mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true),
            ProducerOrder.class
    );
  }

  public ProducerOrderHistory getCurrentProducerOrderHisotry(String currentProducerId) {
    final Business currentProducerBusiness = businessRepository.findByProducerId(currentProducerId)
            .orElseThrow(BusinessNotFoundException::new);
    final List<ProducerOrder> clientOrders = producerOrderRepository.findAllByBusinessIdOrderByOrderDateDesc(currentProducerBusiness.getBusinessId());

    if (clientOrders.isEmpty()) {
      return ProducerOrderHistory.builder()
              .orderHistory(new HashMap<>())
              .build();
    }

    final List<ProducerOrderHistoryRecord> orderHistoryRecords = clientOrders.stream()
            .map(order -> {
              final Double totalPrice = computeTotalPrice(order.getOrderItems());
              return ProducerOrderHistoryRecord.builder()
                      .clientOrderId(order.getClientOrderId())
                      .deliveryAddress(order.getDeliveryAddress())
                      .totalPrice(totalPrice)
                      .orderDate(order.getOrderDate())
                      .build();
            })
            .toList();

    final Map<String, List<ProducerOrderHistoryRecord>> orderHistory = orderHistoryRecords.stream()
            .collect(Collectors.groupingBy(
                    clientOrderHistoryRecord -> getFormattedOrderDate(clientOrderHistoryRecord.getOrderDate()),
                    LinkedHashMap::new,
                    Collectors.toList()
            ));

    return ProducerOrderHistory.builder()
            .orderHistory(orderHistory)
            .build();
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

  private String getFormattedOrderDate(LocalDateTime orderDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.FRENCH);
    return StringUtils.capitalize(orderDate.format(formatter));
  }
}
