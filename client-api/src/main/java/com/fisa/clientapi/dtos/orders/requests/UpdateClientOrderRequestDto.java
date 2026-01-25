package com.fisa.clientapi.dtos.orders.requests;

import com.fisa.clientapi.dtos.AddressDto;
import com.fisa.clientapi.dtos.orders.responses.ProducerOrderDto;
import com.fisa.clientapi.models.ProducerOrder;
import com.fisa.clientapi.models.UpdateClientOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClientOrderRequestDto {

  private String clientOrderId;
  private String clientId;
  private Map<String, List<ProducerOrderDto>> producerOrders;
  private AddressDto deliveryAddress;
  private String email;
  private String phone;

  public static UpdateClientOrderRequest toEntity(UpdateClientOrderRequestDto updateClientOrderRequestDto) {
    return UpdateClientOrderRequest.builder()
            .clientOrderId(updateClientOrderRequestDto.getClientOrderId())
            .clientId(updateClientOrderRequestDto.getClientId())
            .producerOrders(mapProducerOrdersToEntity(updateClientOrderRequestDto.getProducerOrders()))
            .deliveryAddress(AddressDto.toEntity(updateClientOrderRequestDto.getDeliveryAddress()))
            .email(updateClientOrderRequestDto.getEmail())
            .phone(updateClientOrderRequestDto.getPhone())
            .build();
  }

  private static Map<String, List<ProducerOrder>> mapProducerOrdersToEntity(Map<String, List<ProducerOrderDto>> source) {

    if (source == null) {
      return Collections.emptyMap();
    }

    return source.entrySet()
            .stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue()
                            .stream()
                            .map(ProducerOrderDto::toEntity)
                            .toList()
            ));
  }

}
