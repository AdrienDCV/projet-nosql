package com.fisa.producerapi.dtos.producerOrders.requests;

import com.fisa.producerapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UpdateProducerOrderStatusRequestDto {

  private String producerOrderId;
  private OrderStatus newOrderStatus;

}
