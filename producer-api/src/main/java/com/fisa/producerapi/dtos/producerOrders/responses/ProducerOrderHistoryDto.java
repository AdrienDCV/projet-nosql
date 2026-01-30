package com.fisa.producerapi.dtos.producerOrders.responses;

import com.fisa.producerapi.models.ProducerOrderHistory;
import com.fisa.producerapi.models.ProducerOrderHistoryRecord;
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
public class ProducerOrderHistoryDto {

  private Map<String, List<ProducerOrderHistoryRecordDto>> orderHistory;

  public static ProducerOrderHistoryDto toDto(ProducerOrderHistory clientOrderHistory) {
    return ProducerOrderHistoryDto.builder()
            .orderHistory(toDtoMap(clientOrderHistory.getOrderHistory()))
            .build();
  }

  private static  Map<String, List<ProducerOrderHistoryRecordDto>> toDtoMap(Map<String, List<ProducerOrderHistoryRecord>> clientOrderHistory) {
    return clientOrderHistory
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().stream()
                            .map(ProducerOrderHistoryRecordDto::toDto)
                            .toList(),
                    (existing, replacement) -> existing,
                    LinkedHashMap::new
            ));
  }

}
