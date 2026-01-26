package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.PaginatedResponseDto;
import com.fisa.producerapi.dtos.products.requests.CreateProductRequestDto;
import com.fisa.producerapi.dtos.products.responses.CreateProductResponseDto;
import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<CreateProductResponseDto> save(@RequestBody CreateProductRequestDto createProductDto) {
    return new ResponseEntity<>(
            CreateProductResponseDto.toDto(
                    productService.createProduct(CreateProductRequestDto.toEntity(createProductDto))
            ),
            HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<PaginatedResponseDto<CreateProductResponseDto>> retrieveAllProducts(@RequestParam(defaultValue = "0") int page,
                                                                                            @RequestParam(defaultValue = "20") int size) {
    final Pageable pageable = PageRequest.of(page, size);

    final Page<Product> products = productService.retrieveAllProducts(pageable);

    final List<CreateProductResponseDto> content = products.getContent().stream().map(CreateProductResponseDto::toDto).toList();

    final PaginatedResponseDto<CreateProductResponseDto> productDtos = PaginatedResponseDto.<CreateProductResponseDto>builder()
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
  public ResponseEntity<CreateProductResponseDto> retrieveProductById(@PathVariable String productId) {
    return new ResponseEntity<>(
            CreateProductResponseDto.toDto(productService.retrieveProductById(productId)),
            HttpStatus.OK
    );
  }

}
