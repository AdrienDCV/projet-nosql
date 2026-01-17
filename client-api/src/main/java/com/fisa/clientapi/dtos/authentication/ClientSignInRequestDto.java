package com.fisa.clientapi.dtos.authentication;

import com.fisa.clientapi.models.ClientSignInRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSignInRequestDto {

  private String username;
  private String password;

  public static ClientSignInRequest toEntity(ClientSignInRequestDto clientSignInRequestDto) {
    return ClientSignInRequest.builder()
            .username(clientSignInRequestDto.getUsername())
            .password(clientSignInRequestDto.getPassword())
            .build();
  }

}
