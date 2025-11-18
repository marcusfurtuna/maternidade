package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.clinica.maternidade.model.Vacinacao;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
  List<Vacinacao> findByGestacaoId(Long gestacaoId);
}