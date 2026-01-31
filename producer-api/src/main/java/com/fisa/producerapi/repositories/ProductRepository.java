package com.fisa.producerapi.repositories;

import com.fisa.producerapi.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
  Optional<Product> findByProductId(String productId);

  Page<Product> findByLabelRegex(String regex, Pageable pageable);

  List<Product> findAllByBusinessId(String businessId);
}
