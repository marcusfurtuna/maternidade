package com.clinica.maternidade.exame;

import com.clinica.maternidade.gestacao.GestacaoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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