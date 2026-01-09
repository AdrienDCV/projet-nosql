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
public class CreateProductDto {

  private String businessId;
  private String label;
  private Double price;
  private Integer quantity;
  private UniteMesure uniteMesure;

  public static Product toEntity(CreateProductDto createProductDto) {
    return Product.builder()
            .businessId(createProductDto.getBusinessId())
            .label(createProductDto.getLabel())
            .price(createProductDto.getPrice())
            .quantity(createProductDto.getQuantity())
            .uniteMesure(createProductDto.getUniteMesure())
            .build();
  }

}
