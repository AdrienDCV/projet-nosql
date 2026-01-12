package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.ClientOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderRepository extends MongoRepository<ClientOrder, String> {
}
