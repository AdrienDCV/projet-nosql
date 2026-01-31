package com.fisa.producerapi.dtos.businesses.requests;

import com.fisa.producerapi.dtos.addresses.AddressDto;
import com.fisa.producerapi.models.CreateBusinessRequest;
import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBusinessRequestDto {

  private String name;
  private AddressDto address;
  private Profession profession;
  private String description;
  private String phone;
  private String email;
  private String producerId;

  public static CreateBusinessRequest toEntity(CreateBusinessRequestDto createBusinessRequestDto){
    return CreateBusinessRequest.builder()
            .name(createBusinessRequestDto.getName())
            .address(AddressDto.toEntity(createBusinessRequestDto.getAddress()))
            .profession(createBusinessRequestDto.getProfession())
            .description(createBusinessRequestDto.getDescription())
            .phone(createBusinessRequestDto.getPhone())
            .email(createBusinessRequestDto.getEmail())
            .producerId(createBusinessRequestDto.getProducerId())
            .build();
  }

}
