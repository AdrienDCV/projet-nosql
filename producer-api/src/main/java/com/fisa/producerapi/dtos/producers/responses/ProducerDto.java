package com.fisa.producerapi.dtos.producers.responses;

import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.models.enums.Role;
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

  private String producerId;
  private String lastname;
  private String firstname;
  private String email;
  private String phone;
  private Role role;
  private boolean businessCreated;

  public static Producer toEntity(ProducerDto producerDto) {
    return Producer.builder()
            .producerId(producerDto.getProducerId())
            .lastname(producerDto.getLastname())
            .firstname(producerDto.getFirstname())
            .email(producerDto.getEmail())
            .phone(producerDto.getPhone())
            .role(producerDto.getRole())
            .businessCreated(producerDto.isBusinessCreated())
            .build();
  }

  public static ProducerDto toDto(Producer producer) {
    return ProducerDto.builder()
            .producerId(producer.getProducerId())
            .lastname(producer.getLastname())
            .firstname(producer.getFirstname())
            .email(producer.getEmail())
            .phone(producer.getPhone())
            .role(producer.getRole())
            .businessCreated(producer.isBusinessCreated())
            .build();
  }

}