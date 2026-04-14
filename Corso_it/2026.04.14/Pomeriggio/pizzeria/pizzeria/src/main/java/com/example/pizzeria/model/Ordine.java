package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dataOra = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatoOrdine stato = StatoOrdine.IN_ATTESA;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("ordini")
    private Utente utente;

    @ManyToMany
    @JoinTable(
        name = "ordine_pizza",
        joinColumns = @JoinColumn(name = "ordine_id"),
        inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizze;

    @ManyToMany
    @JoinTable(
        name = "ordine_extra",
        joinColumns = @JoinColumn(name = "ordine_id"),
        inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private List<Extra> extra;

    public enum StatoOrdine {
        IN_ATTESA,
        IN_PREPARAZIONE,
        IN_CONSEGNA,
        CONSEGNATO,
        ANNULLATO
    }
}