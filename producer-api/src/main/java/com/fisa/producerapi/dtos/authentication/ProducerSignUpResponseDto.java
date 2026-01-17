package com.fisa.producerapi.dtos.authentication;

import com.fisa.producerapi.dtos.producers.responses.ProducerDto;
import com.fisa.producerapi.models.AuthenticatedProducer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerSignUpResponseDto {

  private String jwt;
  private String message;
  private Boolean enabled;
  private ProducerDto producer;

  public static ProducerSignUpResponseDto toDto(AuthenticatedProducer authenticatedProducer) {
    return ProducerSignUpResponseDto.builder()
            .jwt(authenticatedProducer.getJwt())
            .message(authenticatedProducer.getMessage())
            .enabled(authenticatedProducer.getEnabled())
            .producer(ProducerDto.toDto(authenticatedProducer.getProducer()))
            .build();
  }

}
