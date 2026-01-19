package com.fisa.clientapi.repositories;


import com.fisa.clientapi.models.ProducerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerOrderRepository extends MongoRepository<ProducerOrder, String> {
  List<ProducerOrder> findByClientOrderId(String clientOrderId);

}
