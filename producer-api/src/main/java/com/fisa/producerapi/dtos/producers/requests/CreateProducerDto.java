package com.fisa.producerapi.dtos.producers.requests;

import com.fisa.producerapi.models.Producer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProducerDto {

  private String lastname;
  private String firstname;
  private String email;
  private String phone;
  private String password;

  public static Producer toEntity(CreateProducerDto createProducerDto) {
    return Producer.builder()
            .lastname(createProducerDto.getLastname())
            .firstname(createProducerDto.getFirstname())
            .email(createProducerDto.getEmail())
            .phone(createProducerDto.getPhone())
            .password(createProducerDto.getPassword())
            .build();
  }

}
