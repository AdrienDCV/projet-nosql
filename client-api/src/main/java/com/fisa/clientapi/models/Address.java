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

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Address {

    @Id
    private ObjectId id;

    @Field("ADDRESID")
    private String addressid;
    @Field("STREET")
    private String street;
    @Field("NUMBER")
    private Integer number;
    @Field("POSTALCODE")
    private String postalCode;
    @Field("CITY")
    private String city;
}

