package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.PaginatedResponseDto;
import com.fisa.clientapi.dtos.products.responses.ProductDto;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<PaginatedResponseDto<ProductDto>> retrieveAllProducts(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "20") int size,
          @RequestParam(required = false) String label
  ) {
    final Pageable pageable = PageRequest.of(page, size);

    final Page<Product> products;

    if (label == null || label.isEmpty()) {
      products = productService.retrieveAllProducts(pageable);
    } else {
      products = productService.searchProductsByLabel(label, pageable);
    }

    final List<ProductDto> content = products.getContent().stream().map(ProductDto::toDto).toList();

    final PaginatedResponseDto<ProductDto> productDtos = PaginatedResponseDto.<ProductDto>builder()
            .content(content)
            .pageNumber(products.getNumber())
            .pageSize(products.getSize())
            .totalElement(products.getTotalElements())
            .totalPages(products.getTotalPages())
            .build();

    return new ResponseEntity<>(
            productDtos,
            HttpStatus.OK
    );
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductDto> retrieveProductById(@PathVariable String productId) {
    return new ResponseEntity<>(
            ProductDto.toDto(
                    productService.retrieveProductById(productId)
            ),
            HttpStatus.OK
    );
  }

}
