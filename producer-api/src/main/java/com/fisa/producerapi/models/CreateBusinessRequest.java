package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBusinessRequest {

  private String name;
  private Address address;
  private Profession profession;
  private String description;
  private String phone;
  private String email;
  private String producerId;

}
