package com.fisa.clientapi.dtos.orders.responses;

import com.fisa.clientapi.models.ClientOrderHistory;
import com.fisa.clientapi.models.ClientOrderHistoryRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderHistoryDto {

  private Map<String, List<ClientOrderHistoryRecordDto>> orderHistory;

  public static ClientOrderHistoryDto toDto(ClientOrderHistory clientOrderHistory) {
    return ClientOrderHistoryDto.builder()
            .orderHistory(toDtoMap(clientOrderHistory.getOrderHistory()))
            .build();
  }

  private static  Map<String, List<ClientOrderHistoryRecordDto>> toDtoMap(Map<String, List<ClientOrderHistoryRecord>> clientOrderHistory) {
    return clientOrderHistory
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().stream()
                            .map(ClientOrderHistoryRecordDto::toDto)
                            .toList(),
                    (existing, replacement) -> existing,
                    LinkedHashMap::new
            ));
  }
}
