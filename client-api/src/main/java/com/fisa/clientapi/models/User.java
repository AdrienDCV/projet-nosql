package com.fisa.clientapi.models;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("users")
public class User {

  @Id
  @Field("_id")
  private ObjectId id;

  @Field("USERID")
  private UUID userId;
  @Field("LASTNAME")
  private String lastname;
  @Field("FIRSTNAME")
  private String firstname;
  @Field("EMAIL")
  private String email;
  @Field("PASSWORD")
  private String password;

}
