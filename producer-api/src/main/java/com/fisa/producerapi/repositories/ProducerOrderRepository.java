package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.ProducerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerOrderRepository extends MongoRepository<ProducerOrder, String> {

  List<ProducerOrder> findAllByBusinessId(String businessId);

  Optional<ProducerOrder> findByProducerOrderId(String producerOrderId);

  List<ProducerOrder> findAllByBusinessIdOrderByOrderDateDesc(String businessId);
}
