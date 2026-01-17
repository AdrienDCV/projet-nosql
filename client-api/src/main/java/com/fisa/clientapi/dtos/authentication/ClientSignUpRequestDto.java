package com.fisa.clientapi.dtos.authentication;

import com.fisa.clientapi.models.ClientSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSignUpRequestDto {

  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;

  public static ClientSignUpRequest toEntity(ClientSignUpRequestDto clientSignUpRequestDto) {
    return ClientSignUpRequest.builder()
            .username(clientSignUpRequestDto.getUsername())
            .password(clientSignUpRequestDto.getPassword())
            .firstname(clientSignUpRequestDto.getFirstname())
            .lastname(clientSignUpRequestDto.getLastname())
            .email(clientSignUpRequestDto.getEmail())
            .phone(clientSignUpRequestDto.getPhone())
            .build();
  }

}
