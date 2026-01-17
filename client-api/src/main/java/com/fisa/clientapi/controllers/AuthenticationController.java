package com.fisa.clientapi.controllers;

import com.fisa.clientapi.dtos.authentication.ClientSignInRequestDto;
import com.fisa.clientapi.dtos.authentication.ClientSignUpRequestDto;
import com.fisa.clientapi.dtos.authentication.ClientAuthenticationResponseDto;
import com.fisa.clientapi.repositories.ClientRepository;
import com.fisa.clientapi.security.JwtTokenProvider;
import com.fisa.clientapi.services.ClientService;
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

  private final ClientRepository clientRepository;
  private final ClientService clientService;

  private PasswordEncoder passwordEncoder;


  @PostMapping("/sign-up")
  public ResponseEntity<ClientAuthenticationResponseDto> registerUser(@RequestBody ClientSignUpRequestDto clientSignUpRequestDto) {
    return new ResponseEntity<>(
            ClientAuthenticationResponseDto.toDto(
                    clientService.createClient(
                            ClientSignUpRequestDto.toEntity(clientSignUpRequestDto)
                    )
            ),
            HttpStatus.OK);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<ClientAuthenticationResponseDto> signInUser(@RequestBody ClientSignInRequestDto clientSignInRequestDto) {
    return new ResponseEntity<>(
            ClientAuthenticationResponseDto.toDto(
                    clientService.signInClient(
                            ClientSignInRequestDto.toEntity(clientSignInRequestDto)
                    )
            ),
            HttpStatus.OK);
  }

}
