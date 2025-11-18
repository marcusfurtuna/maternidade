package com.clinica.maternidade.config;

import com.clinica.maternidade.usuario.Perfil;
import com.clinica.maternidade.usuario.Usuario;
import com.clinica.maternidade.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.persistence.EntityManager;
import java.util.Set;

@Configuration
public class DataSeeder {
  @Bean
  CommandLineRunner seed(EntityManager em, UsuarioRepository repo, PasswordEncoder encoder) {
    return args -> {
      var q = em.createQuery("SELECT p FROM Perfil p WHERE p.nome IN ('ADMIN','ATENDENTE','PROFISSIONAL_SAUDE')", Perfil.class);
      var perfis = q.getResultList();
      if (perfis.size() < 3) {
        var p1 = new Perfil(); p1.setNome("ADMIN"); em.persist(p1);
        var p2 = new Perfil(); p2.setNome("ATENDENTE"); em.persist(p2);
        var p3 = new Perfil(); p3.setNome("PROFISSIONAL_SAUDE"); em.persist(p3);
        em.flush();
      }
      if (repo.findByUsername("admin").isEmpty()) {
        var adminRole = em.createQuery("SELECT p FROM Perfil p WHERE p.nome = 'ADMIN'", Perfil.class).getSingleResult();
        var u = new Usuario();
        u.setNome("Administrador");
        u.setUsername("admin");
        u.setSenhaHash(encoder.encode("admin123"));
        u.setPerfis(Set.of(adminRole));
        repo.save(u);
      }
    };
  }
}