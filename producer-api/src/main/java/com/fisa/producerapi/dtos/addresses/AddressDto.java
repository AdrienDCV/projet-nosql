package com.fisa.producerapi.dtos.addresses;

import com.fisa.producerapi.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

  private String street;
  private Integer number;
  private String postalCode;
  private String city;

  public static AddressDto toDto(Address address){
    return AddressDto.builder()
            .street(address.getStreet())
            .number(address.getNumber())
            .postalCode(address.getPostalCode())
            .city(address.getCity())
            .build();
  }

  public static Address toEntity(AddressDto addressDto){
    return Address.builder()
            .street(addressDto.getStreet())
            .number(addressDto.getNumber())
            .postalCode(addressDto.getPostalCode())
            .city(addressDto.getCity())
            .build();
  }

}
