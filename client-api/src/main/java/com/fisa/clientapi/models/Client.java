package com.fisa.clientapi.models;

import com.fisa.clientapi.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("clients")
public class Client {

  @Id
  private ObjectId id;

  @Field("username")
  private String username;
  @Field("password")
  private String password;
  @Field("email")
  private String email;
  @Field("role")
  private Role role;

  @Field("clientId")
  private String clientId;
  @Field("lastname")
  private String lastname;
  @Field("firstname")
  private String firstname;
  @Field("phone")
  private String phone;

}
