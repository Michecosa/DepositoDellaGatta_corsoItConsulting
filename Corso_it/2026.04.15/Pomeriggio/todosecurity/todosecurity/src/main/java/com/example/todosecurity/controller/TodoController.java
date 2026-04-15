package com.example.todosecurity.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/todos")
public class TodoController {
  @GetMapping
  public String test() {
    return "Lista Todo!";
  }
}