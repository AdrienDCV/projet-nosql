package com.fisa.clientapi.models;

import com.fisa.clientapi.models.enums.StockStatus;
import com.fisa.clientapi.models.enums.UniteMesure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product {

    @Id
    @Field("_id")
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
    @Field("image")
    private String image;

}
