package com.fisa.producerapi.services;

import com.fisa.producerapi.exceptions.authentication.EmailAlreadyUsedException;
import com.fisa.producerapi.exceptions.producers.ProducerNotFoundException;
import com.fisa.producerapi.models.Producer;
import com.fisa.producerapi.models.AuthenticatedProducer;
import com.fisa.producerapi.models.ProducerSignInRequest;
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

  public Producer getCurrentProducer() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return producerRepository.findByUsername(authentication.getName()).orElseThrow(ProducerNotFoundException::new);
  }

  public AuthenticatedProducer createProducer(ProducerSignUpRequest producerSignUpRequest) {

    if (producerRepository.findByEmail(producerSignUpRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException("Email already registered");
    }

    Producer newProducer = Producer.builder()
            .producerId(UUID.randomUUID().toString())
            .username(producerSignUpRequest.getUsername())
            .password(passwordEncoder.encode(producerSignUpRequest.getPassword()))
            .firstname(producerSignUpRequest.getFirstname())
            .lastname(producerSignUpRequest.getLastname())
            .role(Role.USER)
            .email(producerSignUpRequest.getEmail())
            .phone(producerSignUpRequest.getPhone())
            .businessCreated(false)
            .build();

    Producer createdProducer = producerRepository.save(newProducer);

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    producerSignUpRequest.getUsername(),
                    producerSignUpRequest.getPassword()
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

  public AuthenticatedProducer signInUser(ProducerSignInRequest producerSignInRequest) {
    final Producer existingProducer = producerRepository.findByUsername(producerSignInRequest.getUsername())
            .orElseThrow(ProducerNotFoundException::new);

    final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    producerSignInRequest.getUsername(),
                    producerSignInRequest.getPassword()
            )
    );

    final String token = jwtTokenProvider.generateToken(authentication);

    return AuthenticatedProducer.builder()
            .jwt(token)
            .message("Login successful")
            .enabled(true)
            .producer(existingProducer)
            .build();
  }

  public void signOutProducer() {
    SecurityContextHolder.clearContext();
  }
}
