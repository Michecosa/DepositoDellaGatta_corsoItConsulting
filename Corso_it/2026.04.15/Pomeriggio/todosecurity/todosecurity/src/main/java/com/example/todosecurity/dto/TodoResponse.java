package com.example.todosecurity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponse {
  private Long id;
  private String titolo;
  private String descrizione;
  private boolean completato;
}