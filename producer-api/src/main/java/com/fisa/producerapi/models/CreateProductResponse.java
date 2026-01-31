package com.fisa.producerapi.models;

import com.fisa.producerapi.models.enums.StockStatus;
import com.fisa.producerapi.models.enums.MeasurementUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {

    private String productId;
    private String businessId;
    private String label;
    private String description;
    private String image;
    private Double price;
    private Integer stock;
    private StockStatus stockStatus;
    private MeasurementUnit measurementUnit;
    private String businessName;

}
