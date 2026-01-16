package com.fisa.clientapi.dtos.orders.requests;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.dtos.products.responses.ProductDto;
import com.fisa.clientapi.models.ClientOrder;
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
  private List<ProductDto> products;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;

  public static ClientOrder toEntity(ClientOrderRequestDto createOrderDto) {
    return ClientOrder.builder()
            .clientId(createOrderDto.getClientId())
            .products(createOrderDto.getProducts().stream().map(ProductDto::toEntity).toList())
            .deliveryAddress(AddressDto.toEntity(createOrderDto.getDeliveryAddress()))
            .email(createOrderDto.getEmail())
            .phone(createOrderDto.getPhone())
            .build();
  }

}
