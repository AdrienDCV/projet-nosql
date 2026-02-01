package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.dtos.addresses.AddressDto;
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
  private AddressDto deliveryAddress;
  private String email;
  private String phone;
  private List<ClientOrderItemDto> products;
  private OrderStatus orderStatus;
  private LocalDateTime orderDate;
  private LocalDateTime updatedAt;

  public static ProducerOrderDto toDto(ProducerOrder producerOrder) {
    return ProducerOrderDto.builder()
            .producerOrderId(producerOrder.getProducerOrderId())
            .clientOrderId(producerOrder.getClientOrderId())
            .businessId(producerOrder.getBusinessId())
            .deliveryAddress(AddressDto.toDto(producerOrder.getDeliveryAddress()))
            .email(producerOrder.getEmail())
            .phone(producerOrder.getPhone())
            .products(producerOrder.getClientOrderItems().stream().map(ClientOrderItemDto::toDto).toList())
            .orderStatus(producerOrder.getOrderStatus())
            .orderDate(producerOrder.getOrderDate())
            .updatedAt(producerOrder.getUpdatedAt())
            .build();
  }

}
