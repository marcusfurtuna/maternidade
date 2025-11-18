package com.clinica.maternidade.controller;

import com.clinica.maternidade.repository.GestacaoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.clinica.maternidade.repository.ConsultaPreNatalRepository;
import com.clinica.maternidade.model.ConsultaPreNatal;

@RestController
@RequestMapping("/api/gestacoes/{gestacaoId}/consultas")
public class ConsultaController {
  private final ConsultaPreNatalRepository repo;
  private final GestacaoRepository gestacaoRepo;
  public ConsultaController(ConsultaPreNatalRepository repo, GestacaoRepository gestacaoRepo) { this.repo = repo; this.gestacaoRepo = gestacaoRepo; }
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public List<ConsultaPreNatal> list(@PathVariable Long gestacaoId) { return repo.findByGestacaoId(gestacaoId); }
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public ConsultaPreNatal create(@PathVariable Long gestacaoId, @RequestBody ConsultaPreNatal c) {
    gestacaoRepo.findById(gestacaoId).orElseThrow();
    c.setGestacao(gestacaoRepo.getReferenceById(gestacaoId));
    return repo.save(c);
  }
}