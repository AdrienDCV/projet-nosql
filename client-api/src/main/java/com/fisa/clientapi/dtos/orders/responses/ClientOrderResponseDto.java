package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.models.ClientOrder;
import com.fisa.clientapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderResponseDto {

  private String parentOrderId;
  private String clientId;
  private List<ClientOrderItemDto> orderItems;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;
  private OrderStatus orderStatus;
  private LocalDateTime orderDate;
  private Double totalPrice;

  public static ClientOrderResponseDto toDto(ClientOrder clientOrder){
    return ClientOrderResponseDto.builder()
            .parentOrderId(clientOrder.getClientOrderId())
            .clientId(clientOrder.getClientId())
            .orderItems(clientOrder.getOrderItems().stream().map(ClientOrderItemDto::toDto).toList())
            .deliveryAddress(AddressDto.toDto(clientOrder.getDeliveryAddress()))
            .email(clientOrder.getEmail())
            .phone(clientOrder.getPhone())
            .orderStatus(clientOrder.getOrderStatus())
            .orderDate(clientOrder.getOrderDate())
            .totalPrice(clientOrder.getTotalPrice())
            .build();
  }

}
