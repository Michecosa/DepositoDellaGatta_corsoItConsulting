package com.example.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzeria.model.Extra;


@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {
    Optional<Extra> findByNome(String nome);
}