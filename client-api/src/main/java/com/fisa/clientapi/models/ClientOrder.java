package com.fisa.clientapi.models;

import com.fisa.clientapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
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
  @Field("orderEntries")
  private List<OrderEntry> orderEntries;
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
  @Field("orderStatus")
  private OrderStatus orderStatus;
  @Field("orderDate")
  private LocalDateTime orderDate;
  @Field("totalPrice")
  private Double totalPrice;

}
