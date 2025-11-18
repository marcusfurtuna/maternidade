package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.clinica.maternidade.model.ConsultaPreNatal;

public interface ConsultaPreNatalRepository extends JpaRepository<ConsultaPreNatal, Long> {
  List<ConsultaPreNatal> findByGestacaoId(Long gestacaoId);
}