package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClientOrderRequest {

  private String clientOrderId;
  private String clientId;
  private Map<String, List<ProducerOrder>> producerOrders;
  private Address deliveryAddress;
  private String email;
  private String phone;

}
