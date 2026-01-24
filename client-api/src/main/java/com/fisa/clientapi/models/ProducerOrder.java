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
import com.fisa.clientapi.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("producerOrders")
public class ProducerOrder {

  @Id
  private ObjectId id;

  @Field("producerOrderId")
  private String producerOrderId;
  @Field("clientOrderId")
  private String clientOrderId;
  @Field("businessId")
  private String businessId;
  @Field("deliveryAddress")
  private Address deliveryAddress;
  @Field("email")
  private String email;
  @Field("phone")
  private String phone;
  @Field("clientOrderItems")
  private List<ClientOrderItem> clientOrderItems;
  @Field("orderStatus")
  private OrderStatus orderStatus;
  @Field("createdAt")
  private LocalDateTime createdAt;
  @Field("updatedeAt")
  private LocalDateTime updatedAt;

}
