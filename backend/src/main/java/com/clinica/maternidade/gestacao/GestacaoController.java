package com.clinica.maternidade.gestacao;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/gestacoes")
public class GestacaoController {
  private final GestacaoRepository repo;
  public GestacaoController(GestacaoRepository repo) { this.repo = repo; }
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public Gestacao get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
  public Gestacao create(@RequestBody Gestacao g) { return repo.save(g); }
  @GetMapping("/paciente/{pacienteId}")
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public List<Gestacao> listByPaciente(@PathVariable Long pacienteId) { return repo.findByPacienteId(pacienteId); }
}