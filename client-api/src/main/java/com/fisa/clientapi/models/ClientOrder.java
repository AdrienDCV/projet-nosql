package com.fisa.clientapi.models;

import com.fisa.clientapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("clientOrders")
public class ClientOrder {

  @Id
  @Field("_id")
  private ObjectId id;

  @Field("clientOrderId")
  private String clientOrderId;
  @Field("clientId")
  private String clientId;
  @Field("orderItems")
  private List<ClientOrderItem> orderItems;
  @Field("deliveryAddress")
  private Address deliveryAddress;
  @Field("email")
  private String email;
  @Field("phone")
  private String phone;
  @Field("orderStatus")
  private OrderStatus orderStatus;
  @Field("orderDate")
  private LocalDateTime orderDate;
  @Field("updatedAt")
  private LocalDateTime updatedAt;
  @Field("totalPrice")
  private Double totalPrice;

}
