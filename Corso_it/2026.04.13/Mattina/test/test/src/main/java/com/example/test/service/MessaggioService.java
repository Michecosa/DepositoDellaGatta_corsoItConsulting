package com.example.test.service;

import org.springframework.stereotype.Service;

@Service
public class MessaggioService {
  public void saluta() {
    System.out.println("[SERVICE] Ciao pupa");
  }
}
