package com.fisa.clientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("carts")
public class Cart {

    @Id
    private ObjectId id;

    @Field("cartId")
    private String cartId;
    @Field("clientId")
    private String clientId;
    @Field("cartEntries")
    private Map<String, CartEntry> cartEntries;
}
