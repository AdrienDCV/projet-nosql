package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.models.ClientOrderHistoryRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderHistoryRecordDto {

  private String clientOrderId;
  private AddressDto deliveryAddress;
  private Double totalPrice;
  private LocalDateTime orderDate;

  public static ClientOrderHistoryRecordDto toDto(ClientOrderHistoryRecord clientOrderHistoryRecord) {
    return ClientOrderHistoryRecordDto.builder()
            .clientOrderId(clientOrderHistoryRecord.getClientOrderId())
            .deliveryAddress(AddressDto.toDto(clientOrderHistoryRecord.getDeliveryAddress()))
            .totalPrice(clientOrderHistoryRecord.getTotalPrice())
            .orderDate(clientOrderHistoryRecord.getOrderDate())
            .build();
  }

}
