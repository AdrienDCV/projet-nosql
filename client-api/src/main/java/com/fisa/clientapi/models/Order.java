package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistrationManager;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("orders")

public class Order {

    @Id
    private ObjectId id;

    @Field("ORDERID")
    private String orderId;
    @Field("USERID")
    private String userId;
    @Field("ADDRESS")
    private Address address;
    @Field("passWord")
    private String passWord;
    @Field("MAIL")
    private String mail;
    @Field("PRODUCT")
    private List<Order> products;
}
