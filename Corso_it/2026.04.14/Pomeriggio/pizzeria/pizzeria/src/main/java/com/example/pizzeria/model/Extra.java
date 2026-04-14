package com.example.pizzeria.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "extra")
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double prezzo;

    @ManyToMany(mappedBy = "extra")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ordine> ordini;
}