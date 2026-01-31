package com.fisa.producerapi.dtos.products.responses;

import com.fisa.producerapi.models.UpdateProductResponse;
import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.models.enums.MeasurementUnit;
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
  private String description;
  private String image;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private MeasurementUnit measurementUnit;
  private String businessName;

  public static UpdateProductResponseDto toDto(UpdateProductResponse updateProductResponse) {
    return UpdateProductResponseDto.builder()
            .productId(updateProductResponse.getProductId())
            .businessId(updateProductResponse.getBusinessId())
            .label(updateProductResponse.getLabel())
            .image(updateProductResponse.getImage())
            .description(updateProductResponse.getDescription())
            .price(updateProductResponse.getPrice())
            .stock(updateProductResponse.getStock())
            .stockStatus(updateProductResponse.getStockStatus())
            .measurementUnit(updateProductResponse.getMeasurementUnit())
            .businessName(updateProductResponse.getBusinessName())
            .build();
  }

}
