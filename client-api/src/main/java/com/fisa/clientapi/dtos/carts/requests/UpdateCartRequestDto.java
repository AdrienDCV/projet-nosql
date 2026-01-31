package com.fisa.clientapi.dtos.carts.requests;

import com.fisa.clientapi.models.UpdateCartRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartRequestDto {

  private String cartId;
  private List<UpdateCartEntryRequestDto> cartEntries;

  public static UpdateCartRequest toEntity(UpdateCartRequestDto updateCartRequestDto) {
    return UpdateCartRequest.builder()
            .cartId(updateCartRequestDto.cartId)
            .cartEntries(updateCartRequestDto.getCartEntries().stream().map(UpdateCartEntryRequestDto::toEntity).toList())
            .build();
  }

}
