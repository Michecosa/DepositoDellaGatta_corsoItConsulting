package com.example.ToDo.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.ToDo.model.StatoTask;
import com.example.ToDo.model.Todo;
import com.example.ToDo.service.ToDoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class TodoController {

  private final ToDoService service;

  public TodoController(ToDoService service) {
    this.service = service;
  }

  // Restituisce tutti i todo, con filtri opzionali per stato e descrizione
  @GetMapping
  public List<Todo> getAll(
      @RequestParam(required = false) StatoTask stato,
      @RequestParam(required = false) String search) {

      return service.getAll().stream()
          .filter(t -> stato == null || t.getStato() == stato)
          .filter(t -> search == null || (t.getDescrizione() != null && t.getDescrizione().toLowerCase().contains(search.toLowerCase())))
          .sorted(Comparator.comparingInt((Todo t) -> t.getPriorita() != null ? t.getPriorita() : 3)
              .thenComparing(t -> t.getDescrizione() != null ? t.getDescrizione() : ""))
          .collect(Collectors.toList());
  }

  // Restituisce un singolo todo per id
  @GetMapping("/{id}")
  public Todo getById(@PathVariable Long id) {
    return service.getById(id).orElse(null);
  }

  // Crea un nuovo todo, parte sempre da TODO
  @PostMapping
  public Todo crea(@Valid @RequestBody Todo todo) {
    todo.setStato(StatoTask.TODO);
    return service.create(todo);
  }

  // Aggiorna lo stato secondo il flusso permesso
  @PutMapping("/{id}")
  public Object aggiornaStato(@PathVariable Long id, @Valid @RequestBody Todo aggiornato) {
    Todo todo = service.getById(id).orElse(null);

    if (todo == null) return "Todo non trovato";

    StatoTask statoAttuale = todo.getStato();
    StatoTask statoNuovo = aggiornato.getStato();

    /* 
      tasitioneValida è true SE
        sono in TODO & voglio andare a IN_PROGRESS oppure CANCELLED
          oppure
        sono in IN_PROGRESS & voglio andare a DONE oppure CENCELLED
    */
    boolean transitioneValida =
        (statoAttuale == StatoTask.TODO && (statoNuovo == StatoTask.IN_PROGRESS || statoNuovo == StatoTask.CANCELLED)) ||
        (statoAttuale == StatoTask.IN_PROGRESS && (statoNuovo == StatoTask.DONE || statoNuovo == StatoTask.CANCELLED));

    if (!transitioneValida) return "Transizione di stato non consentita";

    return service.update(id, aggiornato).orElse(null);
  }

  // Elimina un todo per id
  @DeleteMapping("/{id}")
  public String elimina(@PathVariable Long id) {
    boolean rimosso = service.delete(id);
    return rimosso ? "Todo eliminato" : "Todo non trovato";
  }
}