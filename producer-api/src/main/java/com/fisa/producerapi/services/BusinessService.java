package com.fisa.producerapi.services;

import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.repositories.BusinessRepository;
import com.fisa.producerapi.utils.ShardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessService {

  private final BusinessRepository businessRepository;
  private final ShardProvider shardProvider;

  public Business createBusiness(Business business) {
    business.setBusinessId(UUID.randomUUID().toString());

    Business savedBusiness = businessRepository.save(business);

    // On lance la création lourde du shard en arrière-plan
    shardProvider.createUserShard(savedBusiness.getBusinessId());

    return savedBusiness; // Retour immédiat à l'utilisateur
  }

}
