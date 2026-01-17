package com.fisa.producerapi.dtos.authentication;

import com.fisa.producerapi.models.ProducerSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerSignUpRequestDto {

  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;

  public static ProducerSignUpRequest toEntity(ProducerSignUpRequestDto producerSignUpRequestDto) {
    return ProducerSignUpRequest.builder()
            .username(producerSignUpRequestDto.getUsername())
            .password(producerSignUpRequestDto.getPassword())
            .firstname(producerSignUpRequestDto.getFirstname())
            .lastname(producerSignUpRequestDto.getLastname())
            .email(producerSignUpRequestDto.getEmail())
            .phone(producerSignUpRequestDto.getPhone())
            .build();
  }

}
