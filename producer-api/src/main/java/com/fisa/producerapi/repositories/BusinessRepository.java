package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessRepository extends MongoRepository<Business, UUID> {
}
