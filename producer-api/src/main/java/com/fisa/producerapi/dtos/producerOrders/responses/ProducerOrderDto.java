package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.dtos.addresses.AddressDto;
import com.fisa.producerapi.dtos.products.responses.ProductDto;
import com.fisa.producerapi.models.ProducerOrder;
import com.fisa.producerapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProducerOrderDto {

  private String producerOrderId;
  private String clientOrderId;
  private String businessId;
  private String clientId;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;
  private List<ProductDto> products;
  private OrderStatus orderStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static ProducerOrderDto toDto(ProducerOrder producerOrder) {
    return ProducerOrderDto.builder()
            .producerOrderId(producerOrder.getProducerOrderId())
            .clientOrderId(producerOrder.getClientOrderId())
            .businessId(producerOrder.getBusinessId())
            .clientId(producerOrder.getClientId())
            .deliveryAddress(AddressDto.toDto(producerOrder.getDeliveryAddress()))
            .email(producerOrder.getEmail())
            .phone(producerOrder.getPhone())
            .products(producerOrder.getProducts().stream().map(ProductDto::toDto).toList())
            .orderStatus(producerOrder.getOrderStatus())
            .createdAt(producerOrder.getCreatedAt())
            .updatedAt(producerOrder.getUpdatedAt())
            .build();
  }

}
