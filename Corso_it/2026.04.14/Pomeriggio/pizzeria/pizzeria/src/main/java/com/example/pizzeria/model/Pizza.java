package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pizze")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private Double prezzo;

    @ManyToMany(mappedBy = "pizze")
    private List<Ordine> ordini;
}