package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthResponse;
import com.example.security.service.CustomUserDetailsService;
import com.example.security.service.JwtService;

/**
 * Controller REST per la gestione dell'autenticazione tramite JWT.
 * Espone un endpoint POST /auth/login per autenticare un utente
 * e restituire un token JWT valido.
 */
@RestController
@RequestMapping("/auth") // Tutti gli endpoint di questa classe iniziano con /auth
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private final CustomUserDetailsService userDetailsService;

  // Iniezione delle dipendenze tramite costruttore
  public AuthController(AuthenticationManager authManager, JwtService jwtService,
      CustomUserDetailsService userDetailsService) {
    this.authManager = authManager;
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  /**
   * Endpoint POST /auth/login
   * Riceve username e password (AuthRequest),
   * autentica l'utente e restituisce un token JWT (AuthResponse).
   */
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

    // Autentica l'utente usando AuthenticationManager.
    // Se le credenziali sono errate, Spring Security lancia un'eccezione 401
    // automaticamente.
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    // Recupera l'utente (UserDetails) per ottenere i ruoli e firmare correttamente
    // il JWT.
    // In alternativa puoi fare: UserDetails user = (UserDetails)
    // auth.getPrincipal();
    UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

    // Genera il token JWT a partire dai dati dell'utente
    String token = jwtService.generateToken(user);

    // Restituisce il token al client come risposta JSON
    return ResponseEntity.ok(new AuthResponse(token));
  }
}