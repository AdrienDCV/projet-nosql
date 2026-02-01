package com.fisa.clientapi.dtos.orders.responses;

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
  private String businessId;
  private List<ClientOrderItemDto> clientOrderItems;
  private OrderStatus orderStatus;
  private LocalDateTime orderDate;
  private LocalDateTime updatedAt;

  public static ProducerOrderDto toDto(ProducerOrder producerOrder) {
    return ProducerOrderDto.builder()
            .producerOrderId(producerOrder.getProducerOrderId())
            .businessId(producerOrder.getBusinessId())
            .clientOrderItems(producerOrder.getClientOrderItems().stream().map(ClientOrderItemDto::toDto).toList())
            .orderStatus(producerOrder.getOrderStatus())
            .orderDate(producerOrder.getOrderDate())
            .updatedAt(producerOrder.getUpdatedAt())
            .build();
  }

  public static ProducerOrder toEntity(ProducerOrderDto producerOrderDto) {
    return ProducerOrder.builder()
            .producerOrderId(producerOrderDto.getProducerOrderId())
            .businessId(producerOrderDto.getBusinessId())
            .clientOrderItems(producerOrderDto.getClientOrderItems().stream().map(ClientOrderItemDto::toEntity).toList())
            .orderStatus(producerOrderDto.getOrderStatus())
            .orderDate(producerOrderDto.getOrderDate())
            .updatedAt(producerOrderDto.getUpdatedAt())
            .build();
  }

}
