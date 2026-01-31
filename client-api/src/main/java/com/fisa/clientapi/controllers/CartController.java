package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.carts.responses.CartDto;
import com.fisa.clientapi.models.Client;
import com.fisa.clientapi.services.CartService;
import com.fisa.clientapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

  private final CartService cartService;
  private final ClientService clientService;

  @GetMapping
  public ResponseEntity<CartDto> retrieveClientCart() {
    final Client currentClient = clientService.getCurrentClient();

    return new ResponseEntity<>(
            CartDto.toDto(
                    cartService.getClientCart(currentClient.getClientId())
            ),
            HttpStatus.OK
    );
  }

  /*
  @PutMapping
  public ResponseEntity<CartDto> updateCart(@RequestBody UpdateCartRequestDto updateCartRequestDto) {
    return new ResponseEntity<>(
            CartDto.toDto(
                    cartService.updateCart(
                            UpdateCartRequestDto.toEntity(updateCartRequestDto)
                    )
            ),
            HttpStatus.OK
    );
  }
  */

}