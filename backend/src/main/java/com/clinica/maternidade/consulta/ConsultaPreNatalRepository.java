package com.clinica.maternidade.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultaPreNatalRepository extends JpaRepository<ConsultaPreNatal, Long> {
  List<ConsultaPreNatal> findByGestacaoId(Long gestacaoId);
}