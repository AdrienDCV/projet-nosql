package com.fisa.clientapi.dtos.carts.requests;

import com.fisa.clientapi.models.UpdateCartRequest;
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
public class UpdateCartRequestDto {

  private String cartId;
  private Map<String, UpdateCartEntryRequestDto> cartEntries;

  public static UpdateCartRequest toEntity(UpdateCartRequestDto updateCartRequestDto) {
    return UpdateCartRequest.builder()
            .cartId(updateCartRequestDto.cartId)
            .cartEntries(updateCartRequestDto.getCartEntries().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> UpdateCartEntryRequestDto.toEntity(entry.getValue())
                    )))
            .build();
  }

}
