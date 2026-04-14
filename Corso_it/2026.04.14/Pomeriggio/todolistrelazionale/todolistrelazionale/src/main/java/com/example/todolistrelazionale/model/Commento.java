package com.example.todolistrelazionale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  @JsonIgnore
  private Todo todo;
}
