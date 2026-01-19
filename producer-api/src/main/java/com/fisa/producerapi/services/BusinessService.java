package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.producers.ProducerNotFoundException;
import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.repositories.BusinessRepository;
import com.fisa.producerapi.repositories.ProducerRepository;
import com.fisa.producerapi.utils.ShardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessService {

  private final BusinessRepository businessRepository;
  private final ProducerRepository producerRepository;

  private final ShardProvider shardProvider;

  public Business createBusiness(Business business) {
    business.setBusinessId(UUID.randomUUID().toString());

    final Producer existingProducer = producerRepository
            .findByProducerId(business.getProducerId())
            .orElseThrow(ProducerNotFoundException::new);
    existingProducer.setBusinessCreated(true);

    // On lance la création lourde du shard en arrière-plan
    shardProvider.createUserShard(business.getBusinessId());

    producerRepository.save(existingProducer);

    return businessRepository.save(business);
  }

}
