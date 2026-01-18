package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerSignUpRequest {

  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;

}
