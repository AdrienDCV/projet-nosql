package com.fisa.producerapi.services;

import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerRepository producerRepository;

  public Producer createProducer(Producer newProducer) {
    return producerRepository.save(newProducer);
  }


}
