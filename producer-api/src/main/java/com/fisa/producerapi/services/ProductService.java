package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.products.ProductNotFoundException;
import com.fisa.producerapi.models.CreateProductRequest;
import com.fisa.producerapi.models.CreateProductResponse;
import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.models.UpdateProductRequest;
import com.fisa.producerapi.models.UpdateProductResponse;
import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  private final MongoTemplate mongoTemplate;

  public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
    final Product createdProduct = productRepository.save(Product.builder()
            .productId(UUID.randomUUID().toString())
            .businessId(createProductRequest.getBusinessId())
            .label(createProductRequest.getLabel())
            .image(createProductRequest.getImage())
            .description(createProductRequest.getDescription())
            .price(createProductRequest.getPrice())
            .stock(createProductRequest.getStock())
            .measurementUnit(createProductRequest.getMeasurementUnit())
            .businessName(createProductRequest.getBusinessName())
            .stockStatus(createProductRequest.getStock() > 0 ? StockStatus.IN_STOCK : StockStatus.OUT_OF_STOCK)
            .build());

    return CreateProductResponse.builder()
            .productId(createdProduct.getProductId())
            .businessId(createdProduct.getBusinessId())
            .label(createdProduct.getLabel())
            .image(createdProduct.getImage())
            .description(createdProduct.getDescription())
            .price(createdProduct.getPrice())
            .stock(createdProduct.getStock())
            .stockStatus(createdProduct.getStockStatus())
            .measurementUnit(createdProduct.getMeasurementUnit())
            .businessName(createdProduct.getBusinessName())
            .build();
  }

  public Page<Product> retrieveAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Product retrieveProductById(String productId) {
    return productRepository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
  }

  public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
    final Product existingProduct = productRepository.findByProductId(updateProductRequest.getProductId())
            .orElseThrow(ProductNotFoundException::new);

    final Query query = Query.query(
            Criteria.where("businessId").is(existingProduct.getBusinessId())
                    .and("productId").is(existingProduct.getProductId())
    );

    Update update = new Update()
            .set("label", updateProductRequest.getLabel())
            .set("price", updateProductRequest.getPrice())
            .set("image", updateProductRequest.getImage())
            .set("stock", updateProductRequest.getStock())
            .set("stockStatus", updateProductRequest.getStockStatus())
            .set("description", updateProductRequest.getDescription());

    final Product savedProduct = mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true),
            Product.class
    );
    if (savedProduct == null) throw new ProductNotFoundException();

    return UpdateProductResponse.builder()
            .productId(savedProduct.getProductId())
            .businessId(savedProduct.getBusinessId())
            .label(savedProduct.getLabel())
            .image(savedProduct.getImage())
            .description(savedProduct.getDescription())
            .price(savedProduct.getPrice())
            .stock(savedProduct.getStock())
            .stockStatus(savedProduct.getStockStatus())
            .measurementUnit(savedProduct.getMeasurementUnit())
            .businessName(savedProduct.getBusinessName())
            .build();
  }

  public Page<Product> searchProductsByLabel(String label, Pageable pageable) {
    if (label == null || label.isBlank()) {
      return Page.empty(pageable);
    }

    String regex = "(?i).*" + label + ".*";

    return productRepository.findByLabelRegex(regex, pageable);
  }
}
