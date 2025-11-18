package com.clinica.maternidade.controller;

import com.clinica.maternidade.repository.GestacaoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.clinica.maternidade.repository.ExameRepository;
import com.clinica.maternidade.model.Exame;

@RestController
@RequestMapping("/api/gestacoes/{gestacaoId}/exames")
public class ExameController {
  private final ExameRepository repo;
  private final GestacaoRepository gestacaoRepo;
  public ExameController(ExameRepository repo, GestacaoRepository gestacaoRepo) { this.repo = repo; this.gestacaoRepo = gestacaoRepo; }
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public List<Exame> list(@PathVariable Long gestacaoId) { return repo.findByGestacaoId(gestacaoId); }
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public Exame create(@PathVariable Long gestacaoId, @RequestBody Exame e) {
    gestacaoRepo.findById(gestacaoId).orElseThrow();
    e.setGestacao(gestacaoRepo.getReferenceById(gestacaoId));
    return repo.save(e);
  }
}