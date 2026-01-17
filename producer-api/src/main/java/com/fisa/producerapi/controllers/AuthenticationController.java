package com.fisa.producerapi.controllers;

import com.fisa.producerapi.dtos.authentication.ProducerSignUpRequestDto;
import com.fisa.producerapi.dtos.authentication.ProducerSignUpResponseDto;
import com.fisa.producerapi.repositories.ProducerRepository;
import com.fisa.producerapi.security.JwtTokenProvider;
import com.fisa.producerapi.services.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider tokenProvider;

  private final ProducerRepository producerRepository;
  private final ProducerService producerService;

  private PasswordEncoder passwordEncoder;


  @PostMapping("/sign-up")
  public ResponseEntity<ProducerSignUpResponseDto> registerUser(@RequestBody ProducerSignUpRequestDto producerSignUpRequestDto) {
    return new ResponseEntity<>(
            ProducerSignUpResponseDto.toDto(
                    producerService.createProducer(
                            ProducerSignUpRequestDto.toEntity(producerSignUpRequestDto)
                    )
            ),
            HttpStatus.OK);
  }

}
