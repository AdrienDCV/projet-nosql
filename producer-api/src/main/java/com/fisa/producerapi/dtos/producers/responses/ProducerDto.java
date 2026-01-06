package com.fisa.producerapi.dtos.producers.responses;

import com.fisa.producerapi.models.Producer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerDto {

  private UUID producerId;
  private String lastname;
  private String firstname;
  private String email;
  private String phone;

  public static Producer toEntity(ProducerDto producerDto) {
    return Producer.builder()
            .producerId(producerDto.getProducerId())
            .lastname(producerDto.getLastname())
            .firstname(producerDto.getFirstname())
            .email(producerDto.getEmail())
            .phone(producerDto.getPhone())
            .build();
  }

  public static ProducerDto toDto(Producer producer) {
    return ProducerDto.builder()
            .producerId(producer.getProducerId())
            .lastname(producer.getLastname())
            .firstname(producer.getFirstname())
            .email(producer.getEmail())
            .phone(producer.getPhone())
            .build();
  }

}