package com.fisa.clientapi.dtos.authentication;

import com.fisa.clientapi.dtos.clients.ClientDto;
import com.fisa.clientapi.models.AuthenticatedClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSignUpResponseDto {

  private String jwt;
  private String message;
  private Boolean enabled;
  private ClientDto client;

  public static ClientSignUpResponseDto toDto(AuthenticatedClient authenticatedClient) {
    return ClientSignUpResponseDto.builder()
            .jwt(authenticatedClient.getJwt())
            .message(authenticatedClient.getMessage())
            .enabled(authenticatedClient.getEnabled())
            .client(ClientDto.toDto(authenticatedClient.getClient()))
            .build();
  }

}
