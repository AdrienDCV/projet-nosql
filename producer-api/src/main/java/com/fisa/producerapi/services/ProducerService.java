package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.EmailAlreadyUsedException;
import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.models.AuthenticatedProducer;
import com.fisa.producerapi.models.ProducerSignUpRequest;
import com.fisa.producerapi.models.enums.Role;
import com.fisa.producerapi.repositories.ProducerRepository;
import com.fisa.producerapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerRepository producerRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;


  /**
   * Crée un nouveau producteur et retourne un JWT token
   */
  public AuthenticatedProducer createProducer(ProducerSignUpRequest producerSignUpRequest) {

    // Vérifier que l'email n'existe pas déjà
    if (producerRepository.findByEmail(producerSignUpRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException("Email already registered");
    }

    // Créer et sauvegarder le nouveau producteur
    Producer newProducer = Producer.builder()
            .producerId(UUID.randomUUID().toString())
            .username(producerSignUpRequest.getUsername())
            .password(passwordEncoder.encode(producerSignUpRequest.getPassword()))
            .firstname(producerSignUpRequest.getFirstname())
            .lastname(producerSignUpRequest.getLastname())
            .role(Role.USER)
            .email(producerSignUpRequest.getEmail())
            .phone(producerSignUpRequest.getPhone())
            .build();

    Producer createdProducer = producerRepository.save(newProducer);

    // Authentifier automatiquement après l'inscription
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    producerSignUpRequest.getUsername(),
                    producerSignUpRequest.getPassword()  // Mot de passe en clair !
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(authentication);

    log.info("Producer created and authenticated: {}", createdProducer.getUsername());

    return AuthenticatedProducer.builder()
            .jwt(token)
            .message("Registration successful")
            .enabled(true)
            .producer(createdProducer)
            .build();
  }
}
