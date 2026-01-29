package com.fisa.producerapi.dtos.products.responses;

import com.fisa.producerapi.models.UpdateProductResponse;
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
public class UpdateProductResponseDto {

  private String productId;
  private String businessId;
  private String label;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private UniteMesure uniteMesure;

  public static UpdateProductResponseDto toDto(UpdateProductResponse updateProductResponse) {
    return UpdateProductResponseDto.builder()
            .productId(updateProductResponse.getProductId())
            .businessId(updateProductResponse.getBusinessId())
            .label(updateProductResponse.getLabel())
            .price(updateProductResponse.getPrice())
            .stock(updateProductResponse.getStock())
            .stockStatus(updateProductResponse.getStockStatus())
            .uniteMesure(updateProductResponse.getUniteMesure())
            .build();
  }

}
