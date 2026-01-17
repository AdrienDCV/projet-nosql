package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.ProductNotFoundException;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> retrieveAllProducts() {
    return productRepository.findAll();
  }

  public Page<Product> retrieveAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Product retrieveProductById(String productId) {
    return productRepository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
  }

}
