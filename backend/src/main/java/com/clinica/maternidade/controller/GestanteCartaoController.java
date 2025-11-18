package com.clinica.maternidade.controller;

import com.clinica.maternidade.dto.GestanteCartaoDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.clinica.maternidade.service.GestanteCartaoService;

@RestController
@RequestMapping("/api/gestacoes/{gestacaoId}/cartao")
public class GestanteCartaoController {
  private final GestanteCartaoService service;
  public GestanteCartaoController(GestanteCartaoService service) { this.service = service; }
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public GestanteCartaoDTO get(@PathVariable Long gestacaoId) { return service.getByGestacao(gestacaoId); }
}