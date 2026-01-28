package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.orders.requests.CreateClientOrderRequestDto;
import com.fisa.clientapi.dtos.orders.requests.UpdateClientOrderRequestDto;
import com.fisa.clientapi.dtos.orders.responses.ClientOrderHistoryDto;
import com.fisa.clientapi.dtos.orders.responses.ClientOrderResponseDto;
import com.fisa.clientapi.dtos.orders.responses.ClientOrderDetailsDto;
import com.fisa.clientapi.models.Client;
import com.fisa.clientapi.services.ClientOrderService;
import com.fisa.clientapi.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client-orders")
public class ClientOrderController {

  private final ClientOrderService clientOrderService;
  private final ClientService clientService;

  @PostMapping
  public ResponseEntity<ClientOrderResponseDto> createNewOrder(@RequestBody CreateClientOrderRequestDto createClientOrderRequestDto) {
    return new ResponseEntity<>(
            ClientOrderResponseDto.toDto(
                    clientOrderService.createNewOrder(
                            CreateClientOrderRequestDto.toEntity(createClientOrderRequestDto)
                    )
            ),
            HttpStatus.CREATED
    );
  }

  @GetMapping("/{clientOrderId}")
  public ResponseEntity<ClientOrderDetailsDto> retrieveClientOrderDetails(@PathVariable String clientOrderId) {
    return new ResponseEntity<>(
            ClientOrderDetailsDto.toDto(
                    clientOrderService.getOrderDetails(clientOrderId)
            ),
            HttpStatus.OK
    );
  }

  @GetMapping("/orders-history")
  public ResponseEntity<ClientOrderHistoryDto> retrieveClientOrderHistory() {
    final Client currentClient = clientService.getCurrentClient();

    return new ResponseEntity<>(
            ClientOrderHistoryDto.toDto(
                    clientOrderService.getCurrentClientOrderHisotry(currentClient.getClientId())
            ),
            HttpStatus.OK
    );
  }

  @PutMapping()
  public ResponseEntity<ClientOrderDetailsDto> updateClientOrder(@RequestBody UpdateClientOrderRequestDto updateClientOrderRequestDto) {
    return new ResponseEntity<>(
            ClientOrderDetailsDto.toDto(
                    clientOrderService.updateOrder(
                            UpdateClientOrderRequestDto.toEntity(updateClientOrderRequestDto)
                    )
            ),
            HttpStatus.OK
    );
  }

  @DeleteMapping("/{clientOrderId}")
  public ResponseEntity<Void> deleteClientOrderId(@PathVariable String clientOrderId) {
    clientOrderService.deleteClientOrder(clientOrderId);
    return ResponseEntity.noContent().build();
  }


}
