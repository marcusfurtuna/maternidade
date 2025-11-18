package com.clinica.maternidade.controller;

import com.clinica.maternidade.security.JwtService;
import com.clinica.maternidade.repository.UsuarioRepository;
import com.clinica.maternidade.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

record LoginRequest(String username, String password) {}
record LoginResponse(String token) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final UsuarioRepository repo;
  private final PasswordEncoder encoder;
  private final JwtService jwtService;
  public AuthController(UsuarioRepository repo, PasswordEncoder encoder, JwtService jwtService) {
    this.repo = repo; this.encoder = encoder; this.jwtService = jwtService;
  }
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest req) {
    var u = repo.findByUsername(req.username()).orElseThrow();
    if (!encoder.matches(req.password(), u.getSenhaHash())) throw new RuntimeException("invalid");
    var roles = u.getPerfis().stream().map(p -> "ROLE_" + p.getNome()).collect(Collectors.toList());
    String token = jwtService.generateToken(u.getUsername(), roles);
    return new LoginResponse(token);
  }
  @PostMapping("/logout")
  public void logout() {}
}