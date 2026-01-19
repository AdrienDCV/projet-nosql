package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.Role;
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

  @Field("username")
  private String username;
  @Field("password")
  private String password;
  @Field("email")
  private String email;
  @Field("role")
  private Role role;

  @Field("producerId")
  private String producerId;
  @Field("lastname")
  private String lastname;
  @Field("firstname")
  private String firstname;
  @Field("phone")
  private String phone;
  @Field("businessCreated")
  private boolean businessCreated;

}
