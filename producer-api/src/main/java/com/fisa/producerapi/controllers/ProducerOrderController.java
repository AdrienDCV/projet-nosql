package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.producerOrders.requests.UpdateProducerOrderStatusRequestDto;
import com.fisa.producerapi.dtos.producerOrders.responses.ProducerOrderDto;
import com.fisa.producerapi.dtos.producerOrders.responses.ProducerOrderHistoryDto;
import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.services.ProducerOrderService;
import com.fisa.producerapi.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer-orders")
public class ProducerOrderController {

  private final ProducerOrderService producerOrderService;
  private final ProducerService producerService;

  @GetMapping("/history")
  public ResponseEntity<ProducerOrderHistoryDto> retrieveClientOrderHistory() {
    final Producer currentProducer = producerService.getCurrentProducer();

    return new ResponseEntity<>(
            ProducerOrderHistoryDto.toDto(
                    producerOrderService.getCurrentProducerOrderHisotry(currentProducer.getProducerId())
            ),
            HttpStatus.OK
    );
  }



  @GetMapping("/{businessId}")
  public ResponseEntity<List<ProducerOrderDto>> retrieveAllProducerOrderByBusinessId(@PathVariable String businessId) {
    return new ResponseEntity<>(
            producerOrderService.getProducerOrders(businessId)
                    .stream()
                    .map(ProducerOrderDto::toDto)
                    .toList(),
            HttpStatus.OK
    );
  }

  @PutMapping("/update-status")
  public ResponseEntity<ProducerOrderDto> updateProducerOrderStatus(@RequestBody UpdateProducerOrderStatusRequestDto updateProducerOrderStatusRequestDto) {
    return new ResponseEntity<>(
            ProducerOrderDto.toDto(
                    producerOrderService.updateProducerOrderStatus(
                            updateProducerOrderStatusRequestDto.getProducerOrderId(),
                            updateProducerOrderStatusRequestDto.getNewOrderStatus()
                    )
            ),
            HttpStatus.OK
    );
  }

}
