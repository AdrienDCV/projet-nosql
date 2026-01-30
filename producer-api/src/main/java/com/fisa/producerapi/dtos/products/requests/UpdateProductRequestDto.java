package com.fisa.producerapi.dtos.products.requests;

import com.fisa.producerapi.models.UpdateProductRequest;
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
public class UpdateProductRequestDto {

  private String productId;
  private String businessId;
  private String label;
  private String description;
  private String image;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private MeasurementUnit measurementUnit;

  public static UpdateProductRequest toEntity(UpdateProductRequestDto updateProductRequestDto) {
    return UpdateProductRequest.builder()
            .productId(updateProductRequestDto.getProductId())
            .businessId(updateProductRequestDto.getBusinessId())
            .label(updateProductRequestDto.getLabel())
            .description(updateProductRequestDto.getDescription())
            .image(updateProductRequestDto.getImage())
            .price(updateProductRequestDto.getPrice())
            .stock(updateProductRequestDto.getStock())
            .stockStatus(updateProductRequestDto.getStockStatus())
            .measurementUnit(updateProductRequestDto.getMeasurementUnit())
            .build();
  }

}
