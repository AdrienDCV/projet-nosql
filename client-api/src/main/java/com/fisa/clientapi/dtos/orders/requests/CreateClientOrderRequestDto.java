package com.fisa.clientapi.dtos.orders.requests;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.dtos.orders.responses.ClientOrderItemDto;
import com.fisa.clientapi.models.CreateClientOrderRequest;
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
public class CreateClientOrderRequestDto {

  private String clientId;
  private List<ClientOrderItemDto> orderItems;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;

  public static CreateClientOrderRequest toEntity(CreateClientOrderRequestDto createOrderDto) {
    return CreateClientOrderRequest.builder()
            .clientId(createOrderDto.getClientId())
            .orderItems(createOrderDto.getOrderItems().stream().map(ClientOrderItemDto::toEntity).toList())
            .deliveryAddress(AddressDto.toEntity(createOrderDto.getDeliveryAddress()))
            .email(createOrderDto.getEmail())
            .phone(createOrderDto.getPhone())
            .build();
  }

}
