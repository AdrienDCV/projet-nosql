package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.CartAlreadyExistsException;
import com.fisa.clientapi.exceptions.CartNotFoundException;
import com.fisa.clientapi.exceptions.ProductNotFoundException;
import com.fisa.clientapi.exceptions.QuantityMustBePositiveException;
import com.fisa.clientapi.models.Cart;
import com.fisa.clientapi.models.CartEntry;
import com.fisa.clientapi.models.CreateCartEntryRequest;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.models.UpdateCartRequest;
import com.fisa.clientapi.repositories.CartRepository;
import com.fisa.clientapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public Cart createCart(String clientId) {
    if (cartRepository.findByClientId(clientId).isPresent()) {
      throw new CartAlreadyExistsException();
    }

    return cartRepository.save(
            Cart.builder()
                    .cartId(UUID.randomUUID().toString())
                    .clientId(clientId)
                    .cartEntries(Collections.emptyMap())
                    .build()
    );
  }

  public Cart createCarteEntry(CreateCartEntryRequest createCartEntryRequest, String clientId) {
    if (createCartEntryRequest.getQuantity() < 1) {
      throw new QuantityMustBePositiveException();
    }

    final Product existingProduct = productRepository.findByProductId(createCartEntryRequest.getProductId()).orElseThrow(ProductNotFoundException::new);

    final Cart existingCart = cartRepository.findByClientId(clientId).orElseThrow(CartNotFoundException::new);

    existingCart.getCartEntries().merge(
            createCartEntryRequest.getProductId(),
            createNewCartEntry(createCartEntryRequest, existingProduct, existingCart.getCartId()),
            (existingCartEntry, newCartEntry) -> {
              existingCartEntry.setQuantity(existingCartEntry.getQuantity() + newCartEntry.getQuantity());
              return existingCartEntry;
            }
    );

    return cartRepository.save(existingCart);
  }

  private CartEntry createNewCartEntry(CreateCartEntryRequest request, Product product, String cartId) {
    return CartEntry.builder()
            .cartEntryId(UUID.randomUUID().toString())
            .productId(request.getProductId())
            .productName(product.getLabel())
            .productImage(product.getImage())
            .quantity(request.getQuantity())
            .unitPrice(product.getPrice())
            .build();
  }

  public Cart getClientCart(String clientId) {
    return cartRepository.findByClientId(clientId).orElseThrow(CartNotFoundException::new);
  }

  public Cart updateCart(UpdateCartRequest updateCartRequest) {
    final Cart existingCart = cartRepository.findByCartId(updateCartRequest.getCartId())
            .orElseThrow(CartNotFoundException::new);

    Map<String, CartEntry> updatedEntries = new HashMap<>();

    updateCartRequest.getCartEntries().forEach((productId, updateRequest) -> {
      CartEntry existingEntry = existingCart.getCartEntries().get(productId);
      if (existingEntry != null) {
        existingEntry.setQuantity(updateRequest.getQuantity());
        updatedEntries.put(productId, existingEntry);
      }
    });

    existingCart.setCartEntries(updatedEntries);

    return cartRepository.save(existingCart);
  }
}
