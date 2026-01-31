package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.businesses.requests.CreateBusinessRequestDto;
import com.fisa.producerapi.dtos.businesses.responses.BusinessDto;
import com.fisa.producerapi.dtos.businesses.responses.CreateBusinessResponseDto;
import com.fisa.producerapi.models.CreateBusinessRequest;
import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.services.BusinessService;
import com.fisa.producerapi.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/businesses")
public class BusinessController {

  private final BusinessService businessService;
  private final ProducerService producerService;

  @GetMapping
  public ResponseEntity<BusinessDto> retrieveBusiness() {
    final Producer currentProducer = producerService.getCurrentProducer();

    return new ResponseEntity<>(
            BusinessDto.toDto(
                    businessService.retrieveBusinessByProducerId(currentProducer.getProducerId())
            ),
            HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<CreateBusinessResponseDto> createBusiness(@RequestBody CreateBusinessRequestDto createBusinessRequestDto) {
    return new ResponseEntity<>(
            CreateBusinessResponseDto.toDto(
                    businessService.createBusiness(
                            CreateBusinessRequestDto.toEntity(createBusinessRequestDto)
                    )
            ),
            HttpStatus.CREATED
    );
  }

}
