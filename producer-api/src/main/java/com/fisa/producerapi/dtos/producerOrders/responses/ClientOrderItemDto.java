package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.models.ClientOrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderItemDto {

  private String productId;
  private String businessId;
  private String label;
  private Double unitPrice;
  private Integer quantity;

  public static ClientOrderItemDto toDto(ClientOrderItem clientOrderItem) {
    return ClientOrderItemDto.builder()
            .productId(clientOrderItem.getProductId())
            .businessId(clientOrderItem.getBusinessId())
            .label(clientOrderItem.getLabel())
            .unitPrice(clientOrderItem.getUnitPrice())
            .quantity(clientOrderItem.getQuantity())
            .build();
  }

  public static ClientOrderItem toEntity(ClientOrderItemDto clientOrderItemDto) {
    return ClientOrderItem.builder()
            .productId(clientOrderItemDto.getProductId())
            .businessId(clientOrderItemDto.getBusinessId())
            .label(clientOrderItemDto.getLabel())
            .unitPrice(clientOrderItemDto.getUnitPrice())
            .quantity(clientOrderItemDto.getQuantity())
            .build();
  }

}
