package com.clinica.maternidade.service;

import com.clinica.maternidade.dto.PacienteDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clinica.maternidade.repository.PacienteRepository;
import com.clinica.maternidade.model.Paciente;

@Service
public class PacienteService {
  private final PacienteRepository repo;
  public PacienteService(PacienteRepository repo) { this.repo = repo; }
  @Transactional
  public PacienteDTO create(PacienteDTO dto) {
    Paciente p = toEntity(dto);
    if (repo.existsByCpf(p.getCpf())) throw new IllegalArgumentException("CPF");
    p = repo.save(p);
    return toDTO(p);
  }
  public Page<PacienteDTO> list(String q, Pageable pageable) {
    Page<Paciente> page = (q == null || q.isBlank()) ? repo.findAll(pageable) : repo.findByNomeContainingIgnoreCase(q, pageable);
    return page.map(this::toDTO);
  }
  public PacienteDTO get(Long id) { return toDTO(repo.findById(id).orElseThrow()); }
  @Transactional
  public PacienteDTO update(Long id, PacienteDTO dto) {
    Paciente p = repo.findById(id).orElseThrow();
    p.setNome(dto.getNome());
    p.setTelefone(dto.getTelefone());
    p.setEmail(dto.getEmail());
    p.setEndereco(dto.getEndereco());
    p.setTipoSanguineo(dto.getTipoSanguineo());
    p.setAlergias(dto.getAlergias());
    p.setComorbidades(dto.getComorbidades());
    p.setStatusGestacao(dto.getStatusGestacao());
    return toDTO(repo.save(p));
  }
  @Transactional
  public void delete(Long id) { repo.deleteById(id); }
  private Paciente toEntity(PacienteDTO d) {
    Paciente p = new Paciente();
    p.setNome(d.getNome());
    p.setCpf(d.getCpf());
    p.setDataNascimento(d.getDataNascimento());
    p.setTelefone(d.getTelefone());
    p.setEmail(d.getEmail());
    p.setEndereco(d.getEndereco());
    p.setTipoSanguineo(d.getTipoSanguineo());
    p.setAlergias(d.getAlergias());
    p.setComorbidades(d.getComorbidades());
    p.setStatusGestacao(d.getStatusGestacao());
    return p;
  }
  private PacienteDTO toDTO(Paciente p) {
    PacienteDTO d = new PacienteDTO();
    d.setId(p.getId());
    d.setNome(p.getNome());
    d.setCpf(p.getCpf());
    d.setDataNascimento(p.getDataNascimento());
    d.setTelefone(p.getTelefone());
    d.setEmail(p.getEmail());
    d.setEndereco(p.getEndereco());
    d.setTipoSanguineo(p.getTipoSanguineo());
    d.setAlergias(p.getAlergias());
    d.setComorbidades(p.getComorbidades());
    d.setStatusGestacao(p.getStatusGestacao());
    return d;
  }
}