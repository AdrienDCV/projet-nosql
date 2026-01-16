package com.fisa.producerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("clientOrders")
public class ClientOrder {

  @Field("clientOrderId")
  private String clientOrderId;
  @Field("clientId")
  private String clientId;
  @Field("products")
  private List<Product> products;
  @Field("deliveryAddress")
  private Address deliveryAddress;
  @Field("email")
  private String email;
  @Field("phone")
  private String phone;
  @Field("createdAt")
  private String createdAt;
  @Field("updatedAt")
  private String updatedAt;

}
