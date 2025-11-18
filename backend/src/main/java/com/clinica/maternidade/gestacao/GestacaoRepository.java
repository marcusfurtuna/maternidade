package com.clinica.maternidade.gestacao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GestacaoRepository extends JpaRepository<Gestacao, Long> {
  List<Gestacao> findByPacienteId(Long pacienteId);
}