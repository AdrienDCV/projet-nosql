package com.fisa.producerapi.services;

import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.repositories.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BusinessService {

  private final BusinessRepository businessRepository;

  public Business createBusiness(Business business) {
    return businessRepository.save(business);
  }

}
