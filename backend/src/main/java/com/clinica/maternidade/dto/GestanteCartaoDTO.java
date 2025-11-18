package com.clinica.maternidade.dto;

import java.time.LocalDate;
import java.util.List;

public class GestanteCartaoDTO {
  public static class MaeDTO {
    public Long pacienteId; public String nome; public String cpf; public LocalDate dataNascimento; public String tipoSanguineo; public String alergias; public String comorbidades; public String contato; public String endereco;
  }
  public static class GestacaoDTO {
    public Long gestacaoId; public LocalDate dum; public Integer idadeGestacionalSemanas; public LocalDate dpp; public Integer gestacoesPrevias; public Integer partos; public Integer abortos; public String status; public String unidadeSaude; public LocalDate inicioPreNatal;
  }
  public static class RiscoDTO { public String nivel; public String descricao; }
  public static class ConsultaDTO { public LocalDate data; public Long profissionalId; public String pa; public Double peso; public String observacoes; }
  public static class ExameDTO { public String tipo; public LocalDate data; public String resultadoResumo; }
  public static class VacinacaoDTO { public String vacina; public String dose; public LocalDate data; public String observacao; }
  public static class BebeDTO { public String nome; public String sexo; public LocalDate dataNascimento; public Double pesoNascimento; }
  public MaeDTO mae; public GestacaoDTO gestacao; public RiscoDTO risco; public List<ConsultaDTO> consultas; public List<ExameDTO> exames; public List<VacinacaoDTO> vacinacoes; public BebeDTO bebe;
}