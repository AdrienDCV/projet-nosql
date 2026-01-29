package com.fisa.producerapi.dtos.products.requests;

import com.fisa.producerapi.models.CreateProductRequest;
import com.fisa.producerapi.models.Product;
import com.fisa.producerapi.models.enums.UniteMesure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {

  private String businessId;
  private String label;
  private Double price;
  private Integer stock;
  private UniteMesure uniteMesure;

  public static CreateProductRequest toEntity(CreateProductRequestDto createProductRequestDto) {
    return CreateProductRequest.builder()
            .businessId(createProductRequestDto.getBusinessId())
            .label(createProductRequestDto.getLabel())
            .price(createProductRequestDto.getPrice())
            .stock(createProductRequestDto.getStock())
            .uniteMesure(createProductRequestDto.getUniteMesure())
            .build();
  }

}
