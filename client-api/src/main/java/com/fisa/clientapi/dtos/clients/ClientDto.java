package com.fisa.clientapi.dtos.clients;

import com.fisa.clientapi.models.Client;
import com.fisa.clientapi.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

  private String clientId;
  private String lastname;
  private String firstname;
  private String username;
  private String email;
  private String phone;
  private Role role;

  public static Client toEntity(ClientDto clientDto) {
    return Client.builder()
            .clientId(clientDto.getClientId())
            .lastname(clientDto.getLastname())
            .firstname(clientDto.getFirstname())
            .email(clientDto.getEmail())
            .phone(clientDto.getPhone())
            .role(clientDto.getRole())
            .build();
  }

  public static ClientDto toDto(Client client) {
    return ClientDto.builder()
            .clientId(client.getClientId())
            .lastname(client.getLastname())
            .firstname(client.getFirstname())
            .username(client.getUsername())
            .email(client.getEmail())
            .phone(client.getPhone())
            .role(client.getRole())
            .build();
  }

}
