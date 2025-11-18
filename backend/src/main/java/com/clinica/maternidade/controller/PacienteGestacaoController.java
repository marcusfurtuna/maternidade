package com.clinica.maternidade.controller;

import com.clinica.maternidade.repository.PacienteRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.clinica.maternidade.repository.GestacaoRepository;
import com.clinica.maternidade.model.Gestacao;

@RestController
@RequestMapping("/api/pacientes/{pacienteId}/gestacoes")
public class PacienteGestacaoController {
  private final GestacaoRepository gestacaoRepo;
  private final PacienteRepository pacienteRepo;
  public PacienteGestacaoController(GestacaoRepository gestacaoRepo, PacienteRepository pacienteRepo) {
    this.gestacaoRepo = gestacaoRepo; this.pacienteRepo = pacienteRepo;
  }
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public List<Gestacao> list(@PathVariable Long pacienteId) { return gestacaoRepo.findByPacienteId(pacienteId); }
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
  public Gestacao create(@PathVariable Long pacienteId, @RequestBody Gestacao g) {
    var p = pacienteRepo.findById(pacienteId).orElseThrow();
    g.setPaciente(p);
    return gestacaoRepo.save(g);
  }
}