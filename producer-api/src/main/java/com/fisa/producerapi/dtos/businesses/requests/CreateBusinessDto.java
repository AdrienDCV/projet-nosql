package com.fisa.producerapi.dtos.businesses.requests;

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
public class CreateBusinessDto {

  private String name;
  private AddressDto address;
  private Profession profession;
  private String description;
  private String phone;
  private String email;
  private String producerId;

  public static Business toEntity(CreateBusinessDto createBusinessDto){
    return Business.builder()
            .name(createBusinessDto.getName())
            .address(AddressDto.toEntity(createBusinessDto.getAddress()))
            .profession(createBusinessDto.getProfession())
            .description(createBusinessDto.getDescription())
            .phone(createBusinessDto.getPhone())
            .email(createBusinessDto.getEmail())
            .producerId(createBusinessDto.getProducerId())
            .build();
  }

}
