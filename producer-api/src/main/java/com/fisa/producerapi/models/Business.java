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

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("businesses")
public class Business {

  @Id
  private ObjectId id;

  @Field("BUSINESSID")
  private String businessId;
  @Field("NAME")
  private String name;
  @Field("ADDRESS")
  private String address;
  @Field("PROFESSION")
  private Profession profession;
  @Field("DESCRIPTION")
  private String description;
  @Field("PHONENUMER")
  private String phoneNumber;
  @Field("EMAIL")
  private String email;
  @Field("PRODUCERID")
  private String producerId;

}
