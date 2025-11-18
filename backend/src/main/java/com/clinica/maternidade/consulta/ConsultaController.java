package com.clinica.maternidade.consulta;

import com.clinica.maternidade.gestacao.GestacaoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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