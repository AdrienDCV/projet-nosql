package com.fisa.clientapi.dtos.orders.requests;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.dtos.orders.OrderEntryDto;
import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.OrderEntry;
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
public class ClientOrderRequestDto {

  private String clientId;
  private List<OrderEntry> orderEntries;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;

  public static ClientOrder toEntity(ClientOrderRequestDto createOrderDto) {
    return ClientOrder.builder()
            .clientId(createOrderDto.getClientId())
            .orderEntries(createOrderDto.getOrderEntries().stream().map(OrderEntryDto::toEntity).toList())
            .deliveryAddress(AddressDto.toEntity(createOrderDto.getDeliveryAddress()))
            .email(createOrderDto.getEmail())
            .phone(createOrderDto.getPhone())
            .build();
  }

}
