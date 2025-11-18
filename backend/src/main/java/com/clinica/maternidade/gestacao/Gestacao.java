package com.clinica.maternidade.gestacao;

import com.clinica.maternidade.paciente.Paciente;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gestacoes")
public class Gestacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "paciente_id")
  private Paciente paciente;
  private LocalDate dum;
  private Integer idadeGestacionalSemanas;
  private LocalDate dpp;
  private Integer gestacoesPrevias;
  private Integer partos;
  private Integer abortos;
  private String status;
  private String riscoNivel;
  private String riscoDescricao;
  private String unidadeSaude;
  private java.time.LocalDate inicioPreNatal;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Paciente getPaciente() { return paciente; }
  public void setPaciente(Paciente paciente) { this.paciente = paciente; }
  public LocalDate getDum() { return dum; }
  public void setDum(LocalDate dum) { this.dum = dum; }
  public Integer getIdadeGestacionalSemanas() { return idadeGestacionalSemanas; }
  public void setIdadeGestacionalSemanas(Integer idadeGestacionalSemanas) { this.idadeGestacionalSemanas = idadeGestacionalSemanas; }
  public LocalDate getDpp() { return dpp; }
  public void setDpp(LocalDate dpp) { this.dpp = dpp; }
  public Integer getGestacoesPrevias() { return gestacoesPrevias; }
  public void setGestacoesPrevias(Integer gestacoesPrevias) { this.gestacoesPrevias = gestacoesPrevias; }
  public Integer getPartos() { return partos; }
  public void setPartos(Integer partos) { this.partos = partos; }
  public Integer getAbortos() { return abortos; }
  public void setAbortos(Integer abortos) { this.abortos = abortos; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}