package com.clinica.maternidade.exame;

import com.clinica.maternidade.gestacao.Gestacao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exames")
public class Exame {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "gestacao_id")
  private Gestacao gestacao;
  private String tipo;
  private LocalDate data;
  private String resultadoResumo;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Gestacao getGestacao() { return gestacao; }
  public void setGestacao(Gestacao gestacao) { this.gestacao = gestacao; }
  public String getTipo() { return tipo; }
  public void setTipo(String tipo) { this.tipo = tipo; }
  public LocalDate getData() { return data; }
  public void setData(LocalDate data) { this.data = data; }
  public String getResultadoResumo() { return resultadoResumo; }
  public void setResultadoResumo(String resultadoResumo) { this.resultadoResumo = resultadoResumo; }
}