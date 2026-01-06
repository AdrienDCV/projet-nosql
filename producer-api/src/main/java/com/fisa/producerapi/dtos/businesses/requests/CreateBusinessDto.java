package com.fisa.producerapi.dtos.businesses.requests;

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
public class CreateBusinessDto {

  private String name;
  private String address;
  private Profession profession;
  private String description;
  private String phoneNumber;
  private String email;
  private UUID producerId;

  public static Business toEntity(CreateBusinessDto createBusinessDto){
    return Business.builder()
            .name(createBusinessDto.getName())
            .address(createBusinessDto.getAddress())
            .profession(createBusinessDto.getProfession())
            .description(createBusinessDto.getDescription())
            .phoneNumber(createBusinessDto.getPhoneNumber())
            .email(createBusinessDto.getEmail())
            .producerId(createBusinessDto.getProducerId())
            .build();
  }

}
