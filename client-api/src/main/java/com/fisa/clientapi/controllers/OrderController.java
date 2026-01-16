package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.orders.requests.ClientOrderRequestDto;
import com.fisa.clientapi.dtos.orders.responses.ClientOrderResponseDto;
import com.fisa.clientapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<ClientOrderResponseDto> createNewOrder(@RequestBody ClientOrderRequestDto clientOrderRequestDto) {
    return new ResponseEntity<>(
            ClientOrderResponseDto.toDto(
                    orderService.createNewOrder(
                            ClientOrderRequestDto.toEntity(clientOrderRequestDto)
                    )
            ),
            HttpStatus.CREATED
    );
  }


}
