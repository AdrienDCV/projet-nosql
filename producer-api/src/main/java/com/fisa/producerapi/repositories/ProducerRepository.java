package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProducerRepository extends MongoRepository<Producer, UUID> {
}
