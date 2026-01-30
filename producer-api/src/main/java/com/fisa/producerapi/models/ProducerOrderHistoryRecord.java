package com.fisa.producerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerOrderHistoryRecord {

  private String clientOrderId;
  private Address deliveryAddress;
  private Double totalPrice;
  private LocalDateTime orderDate;

}
