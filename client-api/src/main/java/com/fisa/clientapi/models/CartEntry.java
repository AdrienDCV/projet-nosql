package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartEntry {

    @Id
    private ObjectId id;

    private String cartEntryId;
    private String productId;
    private String productName;
    private String productImage;
    private Integer quantity;
    private Double unitPrice;
}
