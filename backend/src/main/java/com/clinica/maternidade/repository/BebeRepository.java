package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.clinica.maternidade.model.Bebe;

public interface BebeRepository extends JpaRepository<Bebe, Long> {
  Optional<Bebe> findByGestacaoId(Long gestacaoId);
}