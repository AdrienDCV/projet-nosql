package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.models.ClientOrderItem;
import com.fisa.clientapi.models.enums.MeasurementUnit;
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
  private MeasurementUnit measurementUnit;

  public static ClientOrderItemDto toDto(ClientOrderItem clientOrderItem) {
    return ClientOrderItemDto.builder()
            .productId(clientOrderItem.getProductId())
            .businessId(clientOrderItem.getBusinessId())
            .label(clientOrderItem.getLabel())
            .unitPrice(clientOrderItem.getUnitPrice())
            .quantity(clientOrderItem.getQuantity())
            .measurementUnit(clientOrderItem.getMeasurementUnit())
            .build();
  }

  public static ClientOrderItem toEntity(ClientOrderItemDto clientOrderItemDto) {
    return ClientOrderItem.builder()
            .productId(clientOrderItemDto.getProductId())
            .businessId(clientOrderItemDto.getBusinessId())
            .label(clientOrderItemDto.getLabel())
            .unitPrice(clientOrderItemDto.getUnitPrice())
            .quantity(clientOrderItemDto.getQuantity())
            .measurementUnit(clientOrderItemDto.getMeasurementUnit())
            .build();
  }

}
