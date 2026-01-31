package com.fisa.producerapi.models;

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
public class Address {

  private String street;
  private Integer number;
  private String postalCode;
  private String city;
  private String country;

}
