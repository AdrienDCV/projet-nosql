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
public class Business {

  @Id
  private ObjectId id;

  @Field("NAME")
  private String name;
  @Field("ADDRESS")
  private Address address;
  @Field("PROFESSION")
  private String profession;
  @Field("DESCRIPTION")
  private String description;
  @Field("PHONENUMER")
  private String phoneNumber;
  @Field("EMAIL")
  private String email;
  @Field("PRODUCTERID")
  private String producterId;

}
