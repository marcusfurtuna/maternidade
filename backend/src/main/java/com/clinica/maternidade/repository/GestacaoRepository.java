package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.clinica.maternidade.model.Gestacao;

public interface GestacaoRepository extends JpaRepository<Gestacao, Long> {
  List<Gestacao> findByPacienteId(Long pacienteId);
}