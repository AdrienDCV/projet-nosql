package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.producerOrders.ProducerOrderNotFoundException;
import com.fisa.producerapi.models.ProducerOrder;
import com.fisa.producerapi.models.enums.OrderStatus;
import com.fisa.producerapi.repositories.ProducerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProducerOrderService {

  private final ProducerOrderRepository producerOrderRepository;

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
}
