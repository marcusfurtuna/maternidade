package com.clinica.maternidade.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false, unique = true)
  private String username;
  @Column(name = "senha_hash", nullable = false)
  private String senhaHash;
  @ManyToMany
  @JoinTable(name = "usuarios_perfis",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
  private Set<Perfil> perfis;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
  public String getSenhaHash() { return senhaHash; }
  public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
  public Set<Perfil> getPerfis() { return perfis; }
  public void setPerfis(Set<Perfil> perfis) { this.perfis = perfis; }
}