package com.clinica.maternidade.bebe;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BebeRepository extends JpaRepository<Bebe, Long> {
  Optional<Bebe> findByGestacaoId(Long gestacaoId);
}