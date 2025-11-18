package com.clinica.maternidade.exame;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Long> {
  List<Exame> findByGestacaoId(Long gestacaoId);
}