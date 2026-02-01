package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Address {

    @Id
    private ObjectId id;

    @Field("addressId")
    private String addressId;
    @Field("street")
    private String street;
    @Field("number")
    private Integer number;
    @Field("postalCode")
    private String postalCode;
    @Field("city")
    private String city;
    @Field("country")
    private String country;

}

