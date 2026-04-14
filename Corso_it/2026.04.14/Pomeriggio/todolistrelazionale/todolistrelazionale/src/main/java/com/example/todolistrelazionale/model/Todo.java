package com.example.todolistrelazionale.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todo")
public class Todo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String descrizione;

  private Boolean completato;

  @ManyToOne
  @JoinColumn(name = "utente_id", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Utente utente;

  @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Commento> commenti;
}
