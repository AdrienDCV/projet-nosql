package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.models.enums.UniteMesure;
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
public class CreateProductRequest {

    private String businessId;
    private String label;
    private Double price;
    private Integer stock;
    private StockStatus stockStatus;
    private UniteMesure uniteMesure;

}
