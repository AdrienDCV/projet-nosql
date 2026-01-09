package com.fisa.producerapi.services;

import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerRepository producerRepository;

  public Producer createProducer(Producer newProducer) {
    newProducer.setProducerId(UUID.randomUUID().toString());

    return producerRepository.save(newProducer);
  }

}
