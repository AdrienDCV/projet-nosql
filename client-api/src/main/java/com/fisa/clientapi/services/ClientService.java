package com.fisa.clientapi.services;

import com.fisa.clientapi.exceptions.EmailAlreadyUsedException;
import com.fisa.clientapi.models.AuthenticatedClient;
import com.fisa.clientapi.models.Client;
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


  /**
   * Crée un nouveau producteur et retourne un JWT token
   */
  public AuthenticatedClient createClient(ClientSignUpRequest clientSignUpRequest) {

    // Vérifier que l'email n'existe pas déjà
    if (clientRepository.findByEmail(clientSignUpRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException("Email already registered");
    }

    // Créer et sauvegarder le nouveau producteur
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

    // Authentifier automatiquement après l'inscription
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    clientSignUpRequest.getUsername(),
                    clientSignUpRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(authentication);

    log.info("Producer created and authenticated: {}", createdClient.getUsername());

    return AuthenticatedClient.builder()
            .jwt(token)
            .message("Registration successful")
            .enabled(true)
            .client(createdClient)
            .build();
  }

}
