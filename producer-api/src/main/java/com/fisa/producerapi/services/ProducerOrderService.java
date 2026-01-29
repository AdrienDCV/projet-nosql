package com.fisa.producerapi.services;

import com.fisa.producerapi.models.ProducerOrder;
import com.fisa.producerapi.repositories.ProducerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProducerOrderService {

  private final ProducerOrderRepository producerOrderRepository;

  public List<ProducerOrder> getProducerOrders(String businessId) {
    final List<ProducerOrder> existingProducerOrders = producerOrderRepository.findAllByBusinessId(businessId);

    return existingProducerOrders.isEmpty() ? Collections.emptyList() : existingProducerOrders;
  }

}
