package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.ClientNotFoundException;
import com.fisa.clientapi.exceptions.EmailAlreadyUsedException;
import com.fisa.clientapi.models.AuthenticatedClient;
import com.fisa.clientapi.models.Cart;
import com.fisa.clientapi.models.Client;
import com.fisa.clientapi.models.ClientSignInRequest;
import com.fisa.clientapi.models.ClientSignUpRequest;
import com.fisa.clientapi.models.enums.Role;
import com.fisa.clientapi.repositories.ClientRepository;
import com.fisa.clientapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  private final JwtTokenProvider jwtTokenProvider;

  private final CartService cartService;

  public Client getCurrentClient() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return clientRepository.findByUsername(authentication.getName()).orElseThrow(ClientNotFoundException::new);
  }

  public AuthenticatedClient createClient(ClientSignUpRequest clientSignUpRequest) {

    if (clientRepository.findByEmail(clientSignUpRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException("Email already registered");
    }

    Client newClient = Client.builder()
            .clientId(UUID.randomUUID().toString())
            .username(clientSignUpRequest.getUsername())
            .password(passwordEncoder.encode(clientSignUpRequest.getPassword()))
            .firstname(clientSignUpRequest.getFirstname())
            .lastname(clientSignUpRequest.getLastname())
            .role(Role.USER)
            .email(clientSignUpRequest.getEmail())
            .phone(clientSignUpRequest.getPhone())
            .build();

    Client createdClient = clientRepository.save(newClient);

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    clientSignUpRequest.getUsername(),
                    clientSignUpRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(authentication);

    log.info("Client created and authenticated: {}", createdClient.getUsername());

    final Cart createdCart = cartService.createCart(createdClient.getClientId());

    log.info("Client's cart created with ID : {}", createdCart);

    return AuthenticatedClient.builder()
            .jwt(token)
            .message("Registration successful")
            .enabled(true)
            .client(createdClient)
            .cartId(createdCart.getCartId())
            .build();
  }

  public AuthenticatedClient signInClient(ClientSignInRequest clientSignInRequest) {
    final Client existingClient = clientRepository.findByUsername(clientSignInRequest.getUsername())
            .orElseThrow(ClientNotFoundException::new);

    final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    clientSignInRequest.getUsername(),
                    clientSignInRequest.getPassword()
            )
    );

    final String token = jwtTokenProvider.generateToken(authentication);

    final Cart clientCard = cartService.getClientCart(existingClient.getClientId());

    return AuthenticatedClient.builder()
            .jwt(token)
            .message("Login successful")
            .enabled(true)
            .client(existingClient)
            .cartId(clientCard.getCartId())
            .build();
  }

  public void signOutClient() {
    SecurityContextHolder.clearContext();
  }
}
