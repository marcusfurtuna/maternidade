package com.clinica.maternidade.security;

import com.clinica.maternidade.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UsuarioRepository repo;
  public UserDetailsServiceImpl(UsuarioRepository repo) { this.repo = repo; }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var u = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
    var auths = u.getPerfis().stream().map(p -> new SimpleGrantedAuthority("ROLE_" + p.getNome())).collect(Collectors.toList());
    return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getSenhaHash()).authorities(auths).build();
  }
}