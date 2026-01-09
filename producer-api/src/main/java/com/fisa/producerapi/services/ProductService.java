package com.fisa.producerapi.services;

import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(Product newProduct) {
    newProduct.setProductId(UUID.randomUUID().toString());

    return productRepository.save(newProduct);
  }

  public List<Product> retrieveAllProducts() {
    return productRepository.findAll();
  }

}
