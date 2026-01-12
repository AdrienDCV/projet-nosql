package com.fisa.clientapi.dtos;

import com.fisa.clientapi.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

  private String street;
  private Integer number;
  private String postalCode;
  private String city;

  public static Address toEntity(AddressDto addressDto) {
    return Address.builder()
            .street(addressDto.getStreet())
            .number(addressDto.getNumber())
            .postalCode(addressDto.getPostalCode())
            .city(addressDto.getCity())
            .build();
  }

  public static AddressDto toDto(Address address) {
    return AddressDto.builder()
            .street(address.getStreet())
            .number(address.getNumber())
            .postalCode(address.getPostalCode())
            .city(address.getCity())
            .build();
  }


}
