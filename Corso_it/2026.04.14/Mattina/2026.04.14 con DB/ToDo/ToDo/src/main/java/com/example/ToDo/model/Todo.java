package com.example.ToDo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "La descrizione non può essere vuota")
  @Size(min = 3, message = "La descrizione deve avere almeno 3 caratteri")
  private String descrizione;

  private StatoTask stato;

  @NotNull(message = "La priorità non può essere nulla")
  @Min(value = 1, message = "La priorità deve essere almeno 1 (alta)")
  @Max(value = 3, message = "La priorità deve essere al massimo 3 (bassa)")
  private Integer priorita;
}