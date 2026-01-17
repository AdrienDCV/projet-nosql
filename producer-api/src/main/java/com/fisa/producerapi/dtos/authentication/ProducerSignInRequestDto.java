package com.fisa.producerapi.dtos.authentication;

import com.fisa.producerapi.models.ProducerSignInRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerSignInRequestDto {

  private String username;
  private String password;

  public static ProducerSignInRequest toEntity(ProducerSignInRequestDto producerSignInRequestDto) {
    return ProducerSignInRequest.builder()
            .username(producerSignInRequestDto.getUsername())
            .password(producerSignInRequestDto.getPassword())
            .build();
  }

}
