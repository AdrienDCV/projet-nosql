package com.fisa.clientapi.dtos.products.responses;

import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.models.enums.StockStatus;
import com.fisa.clientapi.models.enums.MeasurementUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

  private String productId;
  private String businessId;
  private String label;
  private String description;
  private String image;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private MeasurementUnit measurementUnit;

  public static Product toEntity(ProductDto productDto) {
    return Product.builder()
            .productId(productDto.getProductId())
            .businessId(productDto.getBusinessId())
            .label(productDto.getLabel())
            .description(productDto.getDescription())
            .image(productDto.getImage())
            .price(productDto.getPrice())
            .stock(productDto.getStock())
            .stockStatus(productDto.getStockStatus())
            .measurementUnit(productDto.getMeasurementUnit())
            .build();
  }

  public static ProductDto toDto(Product product) {
    return ProductDto.builder()
            .productId(product.getProductId())
            .businessId(product.getBusinessId())
            .label(product.getLabel())
            .description(product.getDescription())
            .image(product.getImage())
            .price(product.getPrice())
            .stock(product.getStock())
            .stockStatus(product.getStockStatus())
            .measurementUnit(product.getMeasurementUnit())
            .build();
  }
}
