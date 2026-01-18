package com.fisa.clientapi.models;

import com.fisa.clientapi.models.enums.Profession;
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
@Document("businesses")
public class Business {

  @Id
  private ObjectId id;

  @Field("businessId")
  private String businessId;
  @Field("name")
  private String name;
  @Field("address")
  private String address;
  @Field("profession")
  private Profession profession;
  @Field("description")
  private String description;
  @Field("phoneNumber")
  private String phoneNumber;
  @Field("email")
  private String email;
  @Field("producerId")
  private String producerId;

}
