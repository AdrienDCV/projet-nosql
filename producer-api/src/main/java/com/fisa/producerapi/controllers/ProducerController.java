package com.fisa.producerapi.controllers;

import com.fisa.producerapi.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")
public class ProducerController {

  private final ProducerService producerService;

}
