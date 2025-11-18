package com.clinica.maternidade.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  Page<Paciente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
  boolean existsByCpf(String cpf);
}