package com.fisa.producerapi.dtos.products.responses;

import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.models.enums.UniteMesure;
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
  private Double price;
  private Integer quantity;
  private Integer stock;
  private StockStatus stockStatus;
  private UniteMesure uniteMesure;

  public static Product toEntity(ProductDto productDto) {
    return Product.builder()
            .productId(productDto.getProductId())
            .businessId(productDto.getBusinessId())
            .label(productDto.getLabel())
            .price(productDto.getPrice())
            .quantity(productDto.getQuantity())
            .stock(productDto.getStock())
            .stockStatus(productDto.getStockStatus())
            .uniteMesure(productDto.getUniteMesure())
            .build();
  }

  public static ProductDto toDto(Product product) {
    return ProductDto.builder()
            .productId(product.getProductId())
            .businessId(product.getBusinessId())
            .label(product.getLabel())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .stock(product.getStock())
            .stockStatus(product.getStockStatus())
            .uniteMesure(product.getUniteMesure())
            .build();
  }
}
