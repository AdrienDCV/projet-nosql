package com.fisa.clientapi.dtos.orders;

import com.fisa.clientapi.models.OrderEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntryDto {

  private String productId;
  private String businessId;
  private Double unitPrice;
  private Integer quantity;

  public static OrderEntryDto toDto(OrderEntry orderEntry) {
    return OrderEntryDto.builder()
            .productId(orderEntry.getProductId())
            .businessId(orderEntry.getBusinessId())
            .unitPrice(orderEntry.getUnitPrice())
            .quantity(orderEntry.getQuantity())
            .build();
  }

  public static OrderEntry toEntity(OrderEntry orderEntry) {
    return OrderEntry.builder()
            .productId(orderEntry.getProductId())
            .businessId(orderEntry.getBusinessId())
            .unitPrice(orderEntry.getUnitPrice())
            .quantity(orderEntry.getQuantity())
            .build();
  }

}
