package com.clinica.maternidade.controller;

import com.clinica.maternidade.dto.PacienteDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.clinica.maternidade.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
  private final PacienteService service;
  public PacienteController(PacienteService service) { this.service = service; }
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public Page<PacienteDTO> list(@RequestParam(required=false) String q, Pageable pageable) { return service.list(q, pageable); }
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')")
  public PacienteDTO get(@PathVariable Long id) { return service.get(id); }
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
  public PacienteDTO create(@RequestBody PacienteDTO dto) { return service.create(dto); }
  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
  public PacienteDTO update(@PathVariable Long id, @RequestBody PacienteDTO dto) { return service.update(id, dto); }
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) { service.delete(id); }
  @GetMapping("/export")
  @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
  public void exportCsv(HttpServletResponse response) throws IOException {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=pacientes.csv");
    var writer = response.getWriter();
    writer.println("id;nome;cpf;email;telefone");
    for (var p : service.list(null, Pageable.unpaged()).getContent()) {
      writer.println(p.getId() + ";" + p.getNome() + ";" + p.getCpf() + ";" + p.getEmail() + ";" + p.getTelefone());
    }
    writer.flush();
  }
}