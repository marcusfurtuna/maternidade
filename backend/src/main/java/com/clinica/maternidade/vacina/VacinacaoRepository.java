package com.clinica.maternidade.vacina;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
  List<Vacinacao> findByGestacaoId(Long gestacaoId);
}