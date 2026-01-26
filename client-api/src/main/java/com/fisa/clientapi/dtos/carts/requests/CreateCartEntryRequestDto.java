package com.fisa.clientapi.dtos.carts.requests;

import com.fisa.clientapi.models.CreateCartEntryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateCartEntryRequestDto {

  private String cartId;
  private String productId;
  private Integer quantity;

  public static CreateCartEntryRequest toEntity(CreateCartEntryRequestDto createCartEntryRequestDto) {
    return CreateCartEntryRequest.builder()
            .cartId(createCartEntryRequestDto.getCartId())
            .productId(createCartEntryRequestDto.productId)
            .quantity(createCartEntryRequestDto.quantity)
            .build();
  }

}
