package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.models.ClientOrderDetails;
import com.fisa.clientapi.models.ProducerOrder;
import com.fisa.clientapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderDetailsDto {

  private String clientOrderId;
  private String clientId;
  private Map<String, List<ProducerOrderDto>> producerOrders;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;
  private OrderStatus orderStatus;
  private LocalDateTime orderDate;
  private Double totalPrice;

  public static ClientOrderDetailsDto toDto(ClientOrderDetails clientOrderDetails) {
    return ClientOrderDetailsDto.builder()
            .clientOrderId(clientOrderDetails.getClientOrderId())
            .clientId(clientOrderDetails.getClientId())
            .producerOrders(mapProducerOrdersToDto(clientOrderDetails.getProducerOrders()))
            .deliveryAddress(AddressDto.toDto(clientOrderDetails.getDeliveryAddress()))
            .email(clientOrderDetails.getEmail())
            .phone(clientOrderDetails.getPhone())
            .orderStatus(clientOrderDetails.getOrderStatus())
            .orderDate(clientOrderDetails.getOrderDate())
            .totalPrice(clientOrderDetails.getTotalPrice())
            .build();
  }

  private static Map<String, List<ProducerOrderDto>> mapProducerOrdersToDto(Map<String, List<ProducerOrder>> source) {

    if (source == null) {
      return Collections.emptyMap();
    }

    return source.entrySet()
            .stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue()
                            .stream()
                            .map(ProducerOrderDto::toDto)
                            .toList()
            ));
  }

}
