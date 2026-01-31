package com.fisa.clientapi.dtos.carts.responses;


import com.fisa.clientapi.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

  private String cartId;
  private List<CartEntryDto> cartEntries;

  public static CartDto toDto(Cart cart) {
    return CartDto.builder()
            .cartId(cart.getCartId())
            .cartEntries(cart.getCartEntries().stream().map(CartEntryDto::toDto).toList())
            .build();
  }

}
