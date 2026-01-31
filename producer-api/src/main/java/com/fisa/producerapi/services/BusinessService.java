package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.businesses.BusinessNotFoundException;
import com.fisa.producerapi.exceptions.producers.ProducerNotFoundException;
import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.models.CreateBusinessRequest;
import com.fisa.producerapi.models.CreateBusinessResponse;
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

  public CreateBusinessResponse createBusiness(CreateBusinessRequest createBusinessRequest) {
    final Business newBusiness = Business.builder()
            .businessId(UUID.randomUUID().toString())
            .name(createBusinessRequest.getEmail())
            .address(createBusinessRequest.getAddress())
            .profession(createBusinessRequest.getProfession())
            .description(createBusinessRequest.getDescription())
            .phone(createBusinessRequest.getPhone())
            .email(createBusinessRequest.getEmail())
            .producerId(createBusinessRequest.getProducerId())
            .build();

    final Producer existingProducer = producerRepository
            .findByProducerId(newBusiness.getProducerId())
            .orElseThrow(ProducerNotFoundException::new);
    existingProducer.setBusinessCreated(true);

    shardProvider.createUserShard(newBusiness.getBusinessId());

    producerRepository.save(existingProducer);

    final Business createdBusinness = businessRepository.save(newBusiness);

    return CreateBusinessResponse.builder()
            .businessId(createdBusinness.getBusinessId())
            .name(createBusinessRequest.getName())
            .address(createBusinessRequest.getAddress())
            .profession(createBusinessRequest.getProfession())
            .description(createBusinessRequest.getDescription())
            .phone(createBusinessRequest.getPhone())
            .email(createBusinessRequest.getEmail())
            .producerId(createBusinessRequest.getProducerId())
            .build();
  }

  public Business retrieveBusinessByProducerId(String producerId) {
    return businessRepository.findByProducerId(producerId).orElseThrow(BusinessNotFoundException::new);
  }
}
