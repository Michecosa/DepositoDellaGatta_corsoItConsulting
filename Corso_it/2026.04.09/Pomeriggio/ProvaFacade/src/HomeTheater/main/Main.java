package HomeTheater.main;

import HomeTheater.facade.HomeTheaterFacade;

public class Main {
  public static void main(String[] args) {
    // Il client usa solo la "faccia" semplice del sistema
    HomeTheaterFacade cinema = new HomeTheaterFacade();
    
    // Con un solo comando, gestisce tre sottosistemi diversi
    cinema.guardaFilm();
  }
}