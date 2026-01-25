package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.products.ProductNotFoundException;
import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(Product newProduct) {
    newProduct.setProductId(UUID.randomUUID().toString());
    newProduct.setStockStatus(StockStatus.IN_STOCK);

    return productRepository.save(newProduct);
  }

  public Page<Product> retrieveAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Product retrieveProductById(String productId) {
    return productRepository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
  }

}
