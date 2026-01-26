package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.carts.requests.CreateCartEntryRequestDto;
import com.fisa.clientapi.dtos.carts.responses.CartDto;
import com.fisa.clientapi.models.Client;
import com.fisa.clientapi.services.CartService;
import com.fisa.clientapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-entries")
public class CartEntryController {

  private final ClientService clientService;

  private final CartService cartService;

  @PostMapping
  public ResponseEntity<CartDto> createCartEntry(@RequestBody CreateCartEntryRequestDto createCartEntryRequestDto) {
    final Client currentClient = clientService.getCurrentClient();

    return new ResponseEntity<>(
            CartDto.toDto(
                    cartService.createCarteEntry(
                            CreateCartEntryRequestDto.toEntity(createCartEntryRequestDto), currentClient.getClientId()
                    )
            ),
            HttpStatus.CREATED
    );
  }

}
