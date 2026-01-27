package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.clients.ClientDto;
import com.fisa.clientapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

  private final ClientService clientService;

  @GetMapping("/profile")
  public ResponseEntity<ClientDto> retrieveClientProfile() {
    return new ResponseEntity<>(
            ClientDto.toDto(clientService.getCurrentClient()),
            HttpStatus.OK
    );
  }

}
