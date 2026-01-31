package com.fisa.producerapi.dtos.businesses.responses;

import com.fisa.producerapi.dtos.addresses.AddressDto;
import com.fisa.producerapi.models.CreateBusinessResponse;
import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBusinessResponseDto {

  private String businessId;
  private String name;
  private AddressDto address;
  private Profession profession;
  private String description;
  private String phone;
  private String email;
  private String producerId;

  public static CreateBusinessResponse toEntity(CreateBusinessResponseDto businessDto){
    return CreateBusinessResponse.builder()
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

  public static CreateBusinessResponseDto toDto(CreateBusinessResponse createBusinessResponse){
    return CreateBusinessResponseDto.builder()
            .businessId(createBusinessResponse.getBusinessId())
            .name(createBusinessResponse.getName())
            .address(AddressDto.toDto(createBusinessResponse.getAddress()))
            .profession(createBusinessResponse.getProfession())
            .description(createBusinessResponse.getDescription())
            .phone(createBusinessResponse.getPhone())
            .email(createBusinessResponse.getEmail())
            .producerId(createBusinessResponse.getProducerId())
            .build();
  }

}
