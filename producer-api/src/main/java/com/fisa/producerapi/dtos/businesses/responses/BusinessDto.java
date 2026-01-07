package com.fisa.producerapi.dtos.businesses.responses;

import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessDto {

  private String businessId;
  private String name;
  private String address;
  private Profession profession;
  private String description;
  private String phoneNumber;
  private String email;
  private String producerId;

  public static Business toEntity(BusinessDto businessDto){
    return Business.builder()
            .businessId(businessDto.getBusinessId())
            .name(businessDto.getName())
            .address(businessDto.getAddress())
            .profession(businessDto.getProfession())
            .description(businessDto.getDescription())
            .phoneNumber(businessDto.getPhoneNumber())
            .email(businessDto.getEmail())
            .producerId(businessDto.getProducerId())
            .build();
  }

  public static BusinessDto toDto(Business business){
    return BusinessDto.builder()
            .businessId(business.getBusinessId())
            .name(business.getName())
            .address(business.getAddress())
            .profession(business.getProfession())
            .description(business.getDescription())
            .phoneNumber(business.getPhoneNumber())
            .email(business.getEmail())
            .producerId(business.getProducerId())
            .build();
  }

}
