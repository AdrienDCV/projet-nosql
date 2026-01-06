package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.producers.requests.CreateProducerDto;
import com.fisa.producerapi.dtos.producers.responses.ProducerDto;
import com.fisa.producerapi.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")
public class ProducerController {

  private final ProducerService producerService;

  @PostMapping
  public ResponseEntity<ProducerDto> createProducer(@RequestBody CreateProducerDto createProducerDto) {
    return new ResponseEntity<>(
            ProducerDto.toDto(
                    producerService.createProducer(
                            CreateProducerDto.toEntity(createProducerDto)
                    )
            ),
            HttpStatus.CREATED
    );
  }


}
