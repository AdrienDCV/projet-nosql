package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.dtos.addresses.AddressDto;
import com.fisa.producerapi.models.ProducerOrderHistoryRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerOrderHistoryRecordDto {

  private String producerOrderId;
  private AddressDto deliveryAddress;
  private Double totalPrice;
  private LocalDateTime orderDate;

  public static ProducerOrderHistoryRecordDto toDto(ProducerOrderHistoryRecord producerOrderHistoryRecord) {
    return ProducerOrderHistoryRecordDto.builder()
            .producerOrderId(producerOrderHistoryRecord.getClientOrderId())
            .deliveryAddress(AddressDto.toDto(producerOrderHistoryRecord.getDeliveryAddress()))
            .totalPrice(producerOrderHistoryRecord.getTotalPrice())
            .orderDate(producerOrderHistoryRecord.getOrderDate())
            .build();
  }

}
