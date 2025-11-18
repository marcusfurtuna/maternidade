package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.clinica.maternidade.model.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {
  List<Exame> findByGestacaoId(Long gestacaoId);
}