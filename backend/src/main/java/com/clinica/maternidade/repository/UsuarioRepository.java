package com.clinica.maternidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.clinica.maternidade.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsername(String username);
}