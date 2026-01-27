package com.fisa.clientapi.dtos.carts.responses;


import com.fisa.clientapi.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

  private String cartId;
  private Map<String, CartEntryDto> cartEntries;

  public static CartDto toDto(Cart cart) {
    return CartDto.builder()
            .cartId(cart.getCartId())
            .cartEntries(cart.getCartEntries().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> CartEntryDto.toDto(entry.getValue())
                    )))
            .build();
  }

}
