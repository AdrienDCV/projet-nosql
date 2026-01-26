package com.fisa.clientapi.repositories;

import com.fisa.clientapi.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

  Optional<Cart> findByClientId(String clientId);

  Optional<Cart> findByCartId(String cartId);
}
