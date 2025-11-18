package com.clinica.maternidade.model;

import com.clinica.maternidade.model.Gestacao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consultas_prenatal")
public class ConsultaPreNatal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "gestacao_id")
  private Gestacao gestacao;
  private LocalDate data;
  private Long profissionalId;
  private String pa;
  private Double peso;
  private String observacoes;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Gestacao getGestacao() { return gestacao; }
  public void setGestacao(Gestacao gestacao) { this.gestacao = gestacao; }
  public LocalDate getData() { return data; }
  public void setData(LocalDate data) { this.data = data; }
  public Long getProfissionalId() { return profissionalId; }
  public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
  public String getPa() { return pa; }
  public void setPa(String pa) { this.pa = pa; }
  public Double getPeso() { return peso; }
  public void setPeso(Double peso) { this.peso = peso; }
  public String getObservacoes() { return observacoes; }
  public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}