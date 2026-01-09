package com.fisa.producerapi.models;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("producers")
public class Producer {

  @Id
  private ObjectId id;

  @Field("PRODUCERID")
  private String producerId;
  @Field("LASTNAME")
  private String lastname;
  @Field("FIRSTNAME")
  private String firstname;
  @Field("EMAIL")
  private String email;
  @Field("PHONE")
  private String phone;
  @Field("PASSWORD")
  private String password;

}
