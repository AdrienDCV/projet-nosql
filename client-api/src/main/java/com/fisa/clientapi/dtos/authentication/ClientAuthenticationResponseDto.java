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
public class ClientAuthenticationResponseDto {

  private String jwt;
  private String message;
  private Boolean enabled;
  private ClientDto client;
  private String cartId;

  public static ClientAuthenticationResponseDto toDto(AuthenticatedClient authenticatedClient) {
    return ClientAuthenticationResponseDto.builder()
            .jwt(authenticatedClient.getJwt())
            .message(authenticatedClient.getMessage())
            .enabled(authenticatedClient.getEnabled())
            .client(ClientDto.toDto(authenticatedClient.getClient()))
            .cartId(authenticatedClient.getCartId())
            .build();
  }

}
