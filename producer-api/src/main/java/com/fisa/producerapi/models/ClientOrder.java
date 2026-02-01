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
  private List<ClientOrderItem> products;
  @Field("deliveryAddress")
  private Address deliveryAddress;
  @Field("email")
  private String email;
  @Field("phone")
  private String phone;
  @Field("orderDate")
  private String orderDate;
  @Field("updatedAt")
  private String updatedAt;

}
