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
import com.fisa.producerapi.models.enums.UniteMesure;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("products")

public class Product {

    @Id 
    private ObjectId id;

    @Field("PRODUCTID")
    private UUID productId;
    @Field("BUSINESSID")
    private UUID businessId;
    @Field("LABEL")
    private String label;
    @Field("PRICE")
    private Double price;
    @Field("QUANTITY")
    private Integer quantity;
    @Field("UNITEMESURE")
    private UniteMesure uniteMesure;

}
