package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.models.Address;
import com.fisa.clientapi.models.ProducerOrder;
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
public class ProducerOrderDto {

  private String producerOrderId;
  private AddressDto deliveryAddress;
  private List<ClientOrderItemDto> clientOrderItems;
  private OrderStatus orderStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static ProducerOrderDto toDto(ProducerOrder producerOrder) {
    return ProducerOrderDto.builder()
            .producerOrderId(producerOrder.getProducerOrderId())
            .deliveryAddress(AddressDto.toDto(producerOrder.getDeliveryAddress()))
            .clientOrderItems(producerOrder.getClientOrderItems().stream().map(ClientOrderItemDto::toDto).toList())
            .orderStatus(producerOrder.getOrderStatus())
            .createdAt(producerOrder.getCreatedAt())
            .updatedAt(producerOrder.getUpdatedAt())
            .build();
  }

}
