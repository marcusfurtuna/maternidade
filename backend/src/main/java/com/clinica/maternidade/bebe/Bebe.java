package com.clinica.maternidade.bebe;

import com.clinica.maternidade.gestacao.Gestacao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bebes")
public class Bebe {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne @JoinColumn(name = "gestacao_id", unique = true)
  private Gestacao gestacao;
  private String nome;
  private String sexo;
  private LocalDate dataNascimento;
  private Double pesoNascimento;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Gestacao getGestacao() { return gestacao; }
  public void setGestacao(Gestacao gestacao) { this.gestacao = gestacao; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public String getSexo() { return sexo; }
  public void setSexo(String sexo) { this.sexo = sexo; }
  public LocalDate getDataNascimento() { return dataNascimento; }
  public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
  public Double getPesoNascimento() { return pesoNascimento; }
  public void setPesoNascimento(Double pesoNascimento) { this.pesoNascimento = pesoNascimento; }
}