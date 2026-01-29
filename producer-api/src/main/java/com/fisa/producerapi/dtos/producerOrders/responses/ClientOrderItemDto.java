package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.models.ClientOrderItem;
import com.fisa.producerapi.models.enums.UniteMesure;
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
  private UniteMesure uniteMesure;

  public static ClientOrderItemDto toDto(ClientOrderItem clientOrderItem) {
    return ClientOrderItemDto.builder()
            .productId(clientOrderItem.getProductId())
            .businessId(clientOrderItem.getBusinessId())
            .label(clientOrderItem.getLabel())
            .unitPrice(clientOrderItem.getUnitPrice())
            .quantity(clientOrderItem.getQuantity())
            .uniteMesure(clientOrderItem.getUniteMesure())
            .build();
  }

  public static ClientOrderItem toEntity(ClientOrderItemDto clientOrderItemDto) {
    return ClientOrderItem.builder()
            .productId(clientOrderItemDto.getProductId())
            .businessId(clientOrderItemDto.getBusinessId())
            .label(clientOrderItemDto.getLabel())
            .unitPrice(clientOrderItemDto.getUnitPrice())
            .quantity(clientOrderItemDto.getQuantity())
            .uniteMesure(clientOrderItemDto.getUniteMesure())
            .build();
  }

}
