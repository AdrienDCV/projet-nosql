package com.fisa.clientapi.repositories;


import com.fisa.clientapi.models.ProducerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerOrderRepository extends MongoRepository<ProducerOrder, String> {
  List<ProducerOrder> findByClientOrderId(String clientOrderId);

  List<ProducerOrder> findAllByProducerOrderIdIn(List<String> producerOrderIds);

  Optional<ProducerOrder> findByProducerOrderId(String producerOrderId);

  void deleteAllByProducerOrderIdIn(List<String> producerOrderIds);

  void deleteByClientOrderId(String clientOrderId);

  List<ProducerOrder> findAllByClientOrderIdIn(List<String> clientOrderIds);
}
