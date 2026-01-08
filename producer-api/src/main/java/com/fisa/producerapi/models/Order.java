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

    @Field("ORDERID")
    private UUID orderId;
    @Field("BUSINESSID")
    private UUID businessId;
    @Field("CLIENTID")
    private UUID clientId;
    @Field("DELIVERYADDRESS")
    private Address deliveryAddress;
    @Field("MAIL")
    private String mail;
    @Field("PRODUCTS")
    private Map<Product, Integer> products;
    @Field("ORDERSTATUS")
    private OrderStatus orderStatus;

}
