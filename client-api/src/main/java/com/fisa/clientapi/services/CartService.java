package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.CartAlreadyExistsException;
import com.fisa.clientapi.exceptions.CartNotFoundException;
import com.fisa.clientapi.exceptions.ProductNotFoundException;
import com.fisa.clientapi.exceptions.QuantityMustBePositiveException;
import com.fisa.clientapi.models.Cart;
import com.fisa.clientapi.models.CartEntry;
import com.fisa.clientapi.models.CreateCartEntryRequest;
import com.fisa.clientapi.models.Product;
import com.fisa.clientapi.repositories.CartRepository;
import com.fisa.clientapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
                    .cartEntries(Collections.emptyList())
                    .build()
    );
  }

  public Cart createCarteEntry(CreateCartEntryRequest createCartEntryRequest, String clientId) {
    if (createCartEntryRequest.getQuantity() < 1) {
      throw new QuantityMustBePositiveException();
    }

    final Product existingProduct = productRepository.findByProductId(createCartEntryRequest.getProductId()).orElseThrow(ProductNotFoundException::new);

    final Cart existingCart = cartRepository.findByClientId(clientId).orElseThrow(CartNotFoundException::new);

    List<CartEntry> cartEntries = existingCart.getCartEntries();

    Optional<CartEntry> existingEntryOpt = cartEntries.stream()
            .filter(entry -> entry.getProductId().equals(createCartEntryRequest.getProductId()))
            .findFirst();

    if (existingEntryOpt.isPresent()) {
      CartEntry existingEntry = existingEntryOpt.get();
      existingEntry.setQuantity(existingEntry.getQuantity() + createCartEntryRequest.getQuantity()
      );
    } else {
      CartEntry newEntry = createNewCartEntry(createCartEntryRequest, existingProduct);
      cartEntries.add(newEntry);
    }


    return cartRepository.save(existingCart);
  }

  private CartEntry createNewCartEntry(CreateCartEntryRequest request, Product product) {
    return CartEntry.builder()
            .cartEntryId(UUID.randomUUID().toString())
            .productId(request.getProductId())
            .businessId(product.getBusinessId())
            .productName(product.getLabel())
            .productImage(product.getImage())
            .quantity(request.getQuantity())
            .unitPrice(product.getPrice())
            .build();
  }

  public Cart getClientCart(String clientId) {
    return cartRepository.findByClientId(clientId).orElseThrow(CartNotFoundException::new);
  }

}
