package com.example.todosecurity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todos")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titolo;
  private String descrizione;
  private boolean completato;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "utente_id")
  private Utente utente;
}