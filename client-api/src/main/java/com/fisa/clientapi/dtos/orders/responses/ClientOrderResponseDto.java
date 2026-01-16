package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.dtos.products.responses.ProductDto;
import com.fisa.clientapi.models.Address;
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
  private List<ProductDto> products;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;
  private OrderStatus orderStatus;
  private LocalDateTime orderDate;

  public static ClientOrderResponseDto toDto(ClientOrder clientOrder){
    return ClientOrderResponseDto.builder()
            .parentOrderId(clientOrder.getClientOrderId())
            .clientId(clientOrder.getClientId())
            .products(clientOrder.getProducts().stream().map(ProductDto::toDto).toList())
            .deliveryAddress(AddressDto.toDto(clientOrder.getDeliveryAddress()))
            .email(clientOrder.getEmail())
            .phone(clientOrder.getPhone())
            .build();
  }

}
