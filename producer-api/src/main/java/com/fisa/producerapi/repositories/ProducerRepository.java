package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProducerRepository extends MongoRepository<Producer, UUID> {

  Optional<Producer> findByUsername(String username);
  Optional<Producer> findByEmail(String email);

}
