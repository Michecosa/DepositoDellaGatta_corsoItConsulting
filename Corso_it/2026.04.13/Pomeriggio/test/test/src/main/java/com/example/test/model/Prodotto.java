/* package com.example.test.model;

public class Prodotto {

  private Long id;
  private String nome;
  private Double prezzo;

  public Prodotto() {}

  public Prodotto(Long id, String nome, Double prezzo) {
    this.id = id;
    this.nome = nome;
    this.prezzo = prezzo;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public Double getPrezzo() { return prezzo; }
  public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }
} */


 

package com.example.test.model;

// Lombok: genera automaticamente getter, setter, toString, equals, hashCode
import lombok.Data;
// Lombok: genera il costruttore senza argomenti public Prodotto() {}
import lombok.NoArgsConstructor;
// Lombok: genera il costruttore con tutti i campi public Prodotto(Long id, String nome, Double prezzo)
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto {

  private Long id;
  private String nome;
  private Double prezzo;
}

