package com.example.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.service.CustomUserDetailsService;

@Configuration // Indica che questa classe fornisce configurazioni a Spring (equivalente a @Component per i bean)
public class SecurityConfig {

  // Iniettiamo il servizio personalizzato che carica gli utenti dal database
  private final CustomUserDetailsService userDetailsService;

  // Iniettiamo l'oggetto per codificare/verificare le password (es.
  // BCryptPasswordEncoder)
  private final PasswordEncoder passwordEncoder;

  // Costruttore con injection delle dipendenze
  public SecurityConfig(CustomUserDetailsService userDetailsService, PasswordEncoder encoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = encoder;
  }

  /**
   * Definisce il filtro di sicurezza principale usato per ogni richiesta HTTP.
   * Qui si specificano:
   * - le regole di autorizzazione sulle rotte
   * - il tipo di autenticazione (es. form login)
   * - eventuali disabilitazioni come il CSRF
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // Disabilita la protezione CSRF (da lasciare attiva in ambienti reali se usi
        // cookie)
        .csrf(csrf -> csrf.disable())

        // Configura le regole di accesso per le rotte HTTP
        .authorizeHttpRequests(auth -> auth
            // /admin/** accessibile solo agli utenti con ruolo ADMIN
            .requestMatchers("/admin/**").hasRole("ADMIN")

            // /user/** accessibile sia a USER che ADMIN
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")

            // /public/** accessibile a chiunque (anche non autenticato)
            .requestMatchers("/public/**").permitAll()

            // Qualsiasi altra richiesta richiede autenticazione
            .anyRequest().authenticated())

        // Abilita il login con form standard fornito da Spring Security
        .formLogin(form -> form.permitAll())
        .logout(logout -> logout.permitAll());

    // Costruisce e restituisce la catena di filtri di sicurezza
    return http.build();
  }

  /**
   * Definisce il bean AuthenticationManager, necessario se si vuole usare
   * l'autenticazione manuale (es. tramite API REST) oppure configurare
   * manualmente
   * il servizio utenti e il gestore delle password.
   */
  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    // Recupera l'oggetto AuthenticationManagerBuilder per configurarlo
    AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

    // Configura UserDetailsService e PasswordEncoder
    authBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);

    // Costruisce e restituisce l'AuthenticationManager configurato
    return authBuilder.build();
  }
}