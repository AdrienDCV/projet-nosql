package com.fisa.producerapi.dtos.products.responses;

import com.fisa.producerapi.models.CreateProductRequest;
import com.fisa.producerapi.models.CreateProductResponse;
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
public class CreateProductResponseDto {

  private String productId;
  private String businessId;
  private String label;
  private String descriptions;
  private String image;
  private Double price;
  private Integer stock;
  private StockStatus stockStatus;
  private MeasurementUnit measurementUnit;

  public static CreateProductRequest toEntity(CreateProductResponseDto createProductResponseDto) {
    return CreateProductRequest.builder()
            .businessId(createProductResponseDto.getBusinessId())
            .label(createProductResponseDto.getLabel())
            .description(createProductResponseDto.getDescriptions())
            .image(createProductResponseDto.getImage())
            .price(createProductResponseDto.getPrice())
            .stock(createProductResponseDto.getStock())
            .stockStatus(createProductResponseDto.getStockStatus())
            .measurementUnit(createProductResponseDto.getMeasurementUnit())
            .build();
  }

  public static CreateProductResponseDto toDto(CreateProductResponse createProductResponse) {
    return CreateProductResponseDto.builder()
            .productId(createProductResponse.getProductId())
            .businessId(createProductResponse.getBusinessId())
            .label(createProductResponse.getLabel())
            .descriptions(createProductResponse.getDescription())
            .image(createProductResponse.getImage())
            .price(createProductResponse.getPrice())
            .stock(createProductResponse.getStock())
            .stockStatus(createProductResponse.getStockStatus())
            .measurementUnit(createProductResponse.getMeasurementUnit())
            .build();
  }
}
