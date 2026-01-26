package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.ClientOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientOrderRepository extends MongoRepository<ClientOrder, String> {

  Optional<ClientOrder> findByClientOrderId(String clientOrderId);

  void deleteByClientOrderId(String clientOrderId);
}
