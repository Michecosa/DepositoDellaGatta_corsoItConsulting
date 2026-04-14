package com.example.todolistrelazionale.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "commenti")
public class Commento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String testo;

  @ManyToOne
  @JoinColumn(name = "todo_id", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Todo todo;
}
