package com.fisa.producerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fisa.producerapi.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("orders")
public class Order {

    @Id 
    private ObjectId id;

    @Field("orderId")
    private String orderId;
    @Field("clientOrderId")
    private String clientOrderId;
    @Field("businessId")
    private String businessId;
    @Field("clientId")
    private String clientId;
    @Field("deliveryAddress")
    private Address deliveryAddress;
    @Field("email")
    private String email;
    @Field("phone")
    private String phone;
    @Field("products")
    private List<Product> products;
    @Field("orderStatus")
    private OrderStatus orderStatus;
    @Field("createdAt")
    private LocalDateTime createdAt;
    @Field("updatedeAt")
    private LocalDateTime updatedAt;

}
