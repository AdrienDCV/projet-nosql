package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends MongoRepository<Business, String> {
}
