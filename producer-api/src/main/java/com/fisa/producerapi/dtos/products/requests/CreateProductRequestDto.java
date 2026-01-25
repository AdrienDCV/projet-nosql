package com.fisa.producerapi.dtos.products.requests;

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

  public static Product toEntity(CreateProductRequestDto createProductDto) {
    return Product.builder()
            .businessId(createProductDto.getBusinessId())
            .label(createProductDto.getLabel())
            .price(createProductDto.getPrice())
            .stock(createProductDto.getStock())
            .uniteMesure(createProductDto.getUniteMesure())
            .build();
  }

}
