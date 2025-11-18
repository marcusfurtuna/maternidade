package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clinica.maternidade.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  Page<Paciente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
  boolean existsByCpf(String cpf);
}