package com.fisa.producerapi.dtos.products.responses;

import com.fisa.producerapi.models.CreateProductResponse;
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
public class CreateProductResponseDto {

  private String productId;
  private String businessId;
  private String label;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private UniteMesure uniteMesure;

  public static Product toEntity(CreateProductResponseDto createProductResponseDto) {
    return Product.builder()
            .productId(createProductResponseDto.getProductId())
            .businessId(createProductResponseDto.getBusinessId())
            .label(createProductResponseDto.getLabel())
            .price(createProductResponseDto.getPrice())
            .stock(createProductResponseDto.getStock())
            .stockStatus(createProductResponseDto.getStockStatus())
            .uniteMesure(createProductResponseDto.getUniteMesure())
            .build();
  }

  public static CreateProductResponseDto toDto(CreateProductResponse createProductResponse) {
    return CreateProductResponseDto.builder()
            .productId(createProductResponse.getProductId())
            .businessId(createProductResponse.getBusinessId())
            .label(createProductResponse.getLabel())
            .price(createProductResponse.getPrice())
            .stock(createProductResponse.getStock())
            .stockStatus(createProductResponse.getStockStatus())
            .uniteMesure(createProductResponse.getUniteMesure())
            .build();
  }
}
