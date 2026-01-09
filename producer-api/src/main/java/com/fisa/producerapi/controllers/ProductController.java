package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.products.requests.CreateProductDto;
import com.fisa.producerapi.dtos.products.responses.ProductDto;
import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDto> save(@RequestBody CreateProductDto createProductDto) {
    return new ResponseEntity<>(
            ProductDto.toDto(
                    productService.createProduct(CreateProductDto.toEntity(createProductDto))
            ),
            HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> retrieveAllProducts() {
    final List<ProductDto> productDtos = productService.retrieveAllProducts().stream().map(ProductDto::toDto).toList();

    return new ResponseEntity<>(
            productDtos,
            HttpStatus.OK
    );
  }

}
