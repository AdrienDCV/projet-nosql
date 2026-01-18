package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends MongoRepository<Product, String> {
  Optional<Product> findByProductId(String productId);

  Set<Product> findAllByProductIdIn(Set<String> productIds);

}
