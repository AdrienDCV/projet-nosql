package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.StockStatus;
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

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product {

    @Id 
    private ObjectId id;

    @Field("productId")
    private String productId;
    @Field("businessId")
    private String businessId;
    @Field("label")
    private String label;
    @Field("price")
    private Double price;
    @Field("stock")
    private Integer stock;
    @Field("stockStatus")
    private StockStatus stockStatus;
    @Field("uniteMesure")
    private UniteMesure uniteMesure;

}
