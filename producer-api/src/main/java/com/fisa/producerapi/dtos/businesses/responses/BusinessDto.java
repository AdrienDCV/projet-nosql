package com.fisa.producerapi.dtos.businesses.responses;

import com.fisa.producerapi.dtos.addresses.AddressDto;
import com.fisa.producerapi.models.Business;
import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessDto {

  private String businessId;
  private String name;
  private AddressDto address;
  private Profession profession;
  private String description;
  private String phone;
  private String email;
  private String producerId;

  public static Business toEntity(BusinessDto businessDto){
    return Business.builder()
            .businessId(businessDto.getBusinessId())
            .name(businessDto.getName())
            .address(AddressDto.toEntity(businessDto.getAddress()))
            .profession(businessDto.getProfession())
            .description(businessDto.getDescription())
            .phone(businessDto.getPhone())
            .email(businessDto.getEmail())
            .producerId(businessDto.getProducerId())
            .build();
  }

  public static BusinessDto toDto(Business business){
    return BusinessDto.builder()
            .businessId(business.getBusinessId())
            .name(business.getName())
            .address(AddressDto.toDto(business.getAddress()))
            .profession(business.getProfession())
            .description(business.getDescription())
            .phone(business.getPhone())
            .email(business.getEmail())
            .producerId(business.getProducerId())
            .build();
  }

}
