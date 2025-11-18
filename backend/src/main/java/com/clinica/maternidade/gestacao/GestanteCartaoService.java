package com.clinica.maternidade.gestacao;

import com.clinica.maternidade.dto.GestanteCartaoDTO;
import com.clinica.maternidade.paciente.Paciente;
import com.clinica.maternidade.paciente.PacienteRepository;
import com.clinica.maternidade.consulta.ConsultaPreNatalRepository;
import com.clinica.maternidade.exame.ExameRepository;
import com.clinica.maternidade.vacina.VacinacaoRepository;
import com.clinica.maternidade.bebe.BebeRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class GestanteCartaoService {
  private final GestacaoRepository gestacaoRepo;
  private final PacienteRepository pacienteRepo;
  private final ConsultaPreNatalRepository consultaRepo;
  private final ExameRepository exameRepo;
  private final VacinacaoRepository vacRepo;
  private final BebeRepository bebeRepo;
  public GestanteCartaoService(GestacaoRepository g, PacienteRepository p, ConsultaPreNatalRepository c, ExameRepository e, VacinacaoRepository v, BebeRepository b) {
    this.gestacaoRepo = g; this.pacienteRepo = p; this.consultaRepo = c; this.exameRepo = e; this.vacRepo = v; this.bebeRepo = b;
  }
  public GestanteCartaoDTO getByGestacao(Long gestacaoId) {
    var g = gestacaoRepo.findById(gestacaoId).orElseThrow();
    Paciente pac = g.getPaciente();
    var dto = new GestanteCartaoDTO();
    var mae = new GestanteCartaoDTO.MaeDTO();
    mae.pacienteId = pac.getId(); mae.nome = pac.getNome(); mae.cpf = pac.getCpf(); mae.dataNascimento = pac.getDataNascimento(); mae.tipoSanguineo = pac.getTipoSanguineo(); mae.alergias = pac.getAlergias(); mae.comorbidades = pac.getComorbidades(); mae.contato = pac.getTelefone(); mae.endereco = pac.getEndereco();
    var gd = new GestanteCartaoDTO.GestacaoDTO();
    gd.gestacaoId = g.getId(); gd.dum = g.getDum(); gd.idadeGestacionalSemanas = g.getIdadeGestacionalSemanas(); gd.dpp = g.getDpp(); gd.gestacoesPrevias = g.getGestacoesPrevias(); gd.partos = g.getPartos(); gd.abortos = g.getAbortos(); gd.status = g.getStatus(); gd.unidadeSaude = g.getUnidadeSaude(); gd.inicioPreNatal = g.getInicioPreNatal();
    var risco = new GestanteCartaoDTO.RiscoDTO();
    risco.nivel = g.getRiscoNivel(); risco.descricao = g.getRiscoDescricao();
    dto.mae = mae; dto.gestacao = gd; dto.risco = risco;
    dto.consultas = consultaRepo.findByGestacaoId(gestacaoId).stream().map(c -> { var cd = new GestanteCartaoDTO.ConsultaDTO(); cd.data = c.getData(); cd.profissionalId = c.getProfissionalId(); cd.pa = c.getPa(); cd.peso = c.getPeso(); cd.observacoes = c.getObservacoes(); return cd; }).collect(Collectors.toList());
    dto.exames = exameRepo.findByGestacaoId(gestacaoId).stream().map(ex -> { var ed = new GestanteCartaoDTO.ExameDTO(); ed.tipo = ex.getTipo(); ed.data = ex.getData(); ed.resultadoResumo = ex.getResultadoResumo(); return ed; }).collect(Collectors.toList());
    dto.vacinacoes = vacRepo.findByGestacaoId(gestacaoId).stream().map(v -> { var vd = new GestanteCartaoDTO.VacinacaoDTO(); vd.vacina = v.getVacina(); vd.dose = v.getDose(); vd.data = v.getData(); vd.observacao = v.getObservacao(); return vd; }).collect(Collectors.toList());
    dto.bebe = bebeRepo.findByGestacaoId(gestacaoId).map(b -> { var bd = new GestanteCartaoDTO.BebeDTO(); bd.nome = b.getNome(); bd.sexo = b.getSexo(); bd.dataNascimento = b.getDataNascimento(); bd.pesoNascimento = b.getPesoNascimento(); return bd; }).orElse(null);
    return dto;
  }
}