package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.Business;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

public interface BusinessRepository extends MongoRepository<Business, String> {

  Optional<Business> findByBusinessId(String businessId);

  Set<Business> findAllByBusinessIdIn(Set<String> businessIds);
}
