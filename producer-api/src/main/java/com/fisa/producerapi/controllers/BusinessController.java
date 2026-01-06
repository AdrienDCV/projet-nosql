package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.businesses.requests.CreateBusinessDto;
import com.fisa.producerapi.dtos.businesses.responses.BusinessDto;
import com.fisa.producerapi.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/businesses")
public class BusinessController {

  private final BusinessService businessService;

  @PostMapping
  public ResponseEntity<BusinessDto> createBusiness(@RequestBody CreateBusinessDto createBusinessDto) {
    return new ResponseEntity<>(
            BusinessDto.toDto(
                    businessService.createBusiness(
                            CreateBusinessDto.toEntity(createBusinessDto)
                    )
            ),
            HttpStatus.CREATED
    );
  }

}
