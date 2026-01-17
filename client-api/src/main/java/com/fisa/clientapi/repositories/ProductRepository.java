package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
