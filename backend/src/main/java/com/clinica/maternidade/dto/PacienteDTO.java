package com.clinica.maternidade.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PacienteDTO {
  private Long id;
  @NotBlank
  private String nome;
  @Pattern(regexp = "\\d{11}")
  private String cpf;
  private LocalDate dataNascimento;
  private String telefone;
  @Email
  private String email;
  private String endereco;
  private String tipoSanguineo;
  private String alergias;
  private String comorbidades;
  private String statusGestacao;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public String getCpf() { return cpf; }
  public void setCpf(String cpf) { this.cpf = cpf; }
  public LocalDate getDataNascimento() { return dataNascimento; }
  public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
  public String getTelefone() { return telefone; }
  public void setTelefone(String telefone) { this.telefone = telefone; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getEndereco() { return endereco; }
  public void setEndereco(String endereco) { this.endereco = endereco; }
  public String getTipoSanguineo() { return tipoSanguineo; }
  public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }
  public String getAlergias() { return alergias; }
  public void setAlergias(String alergias) { this.alergias = alergias; }
  public String getComorbidades() { return comorbidades; }
  public void setComorbidades(String comorbidades) { this.comorbidades = comorbidades; }
  public String getStatusGestacao() { return statusGestacao; }
  public void setStatusGestacao(String statusGestacao) { this.statusGestacao = statusGestacao; }
}