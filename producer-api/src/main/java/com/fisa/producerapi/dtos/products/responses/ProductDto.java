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
  private String descriptions;
  private String image;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private UniteMesure uniteMesure;

  public static ProductDto toDto(Product product) {
    return ProductDto.builder()
            .productId(product.getProductId())
            .businessId(product.getBusinessId())
            .label(product.getLabel())
            .descriptions(product.getDescription())
            .image(product.getImage())
            .price(product.getPrice())
            .stock(product.getStock())
            .stockStatus(product.getStockStatus())
            .uniteMesure(product.getUniteMesure())
            .build();
  }

}
