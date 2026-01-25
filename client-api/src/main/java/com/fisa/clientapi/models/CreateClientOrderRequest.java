package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClientOrderRequest {

  private String clientId;
  private List<ClientOrderItem> orderItems;
  private Address deliveryAddress;
  private String email;
  private String phone;

}
