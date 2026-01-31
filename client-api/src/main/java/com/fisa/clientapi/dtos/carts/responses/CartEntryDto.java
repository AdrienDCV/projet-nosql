package com.fisa.clientapi.dtos.carts.responses;

import com.fisa.clientapi.models.CartEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartEntryDto {

  private String cartEntryId;
  private String productId;
  private String businessId;
  private String productName;
  private String productImage;
  private Integer quantity;
  private Double unitPrice;

  public static CartEntryDto toDto(CartEntry cartEntry) {
    return CartEntryDto.builder()
            .cartEntryId(cartEntry.getCartEntryId())
            .productId(cartEntry.getProductId())
            .businessId(cartEntry.getBusinessId())
            .productName(cartEntry.getProductName())
            .productImage(cartEntry.getProductImage())
            .quantity(cartEntry.getQuantity())
            .unitPrice(cartEntry.getUnitPrice())
            .build();
  }

}
