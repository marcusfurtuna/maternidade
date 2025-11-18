package com.clinica.maternidade.model;

import com.clinica.maternidade.model.Gestacao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacinacoes")
public class Vacinacao {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) @JoinColumn(name = "gestacao_id")
  private Gestacao gestacao;
  private String vacina;
  private String dose;
  private LocalDate data;
  private String observacao;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Gestacao getGestacao() { return gestacao; }
  public void setGestacao(Gestacao gestacao) { this.gestacao = gestacao; }
  public String getVacina() { return vacina; }
  public void setVacina(String vacina) { this.vacina = vacina; }
  public String getDose() { return dose; }
  public void setDose(String dose) { this.dose = dose; }
  public LocalDate getData() { return data; }
  public void setData(LocalDate data) { this.data = data; }
  public String getObservacao() { return observacao; }
  public void setObservacao(String observacao) { this.observacao = observacao; }
}