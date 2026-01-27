package com.fisa.clientapi.dtos.carts.requests;

import com.fisa.clientapi.models.UpdateCartEntryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartEntryRequestDto {

  private String cartEntryId;
  private String productId;
  private Integer quantity;

  public static UpdateCartEntryRequest toEntity(UpdateCartEntryRequestDto updateCartEntryRequestDto) {
    return UpdateCartEntryRequest.builder()
            .cartEntryId(updateCartEntryRequestDto.getCartEntryId())
            .productId(updateCartEntryRequestDto.getProductId())
            .quantity(updateCartEntryRequestDto.getQuantity())
            .build();
  }

}
