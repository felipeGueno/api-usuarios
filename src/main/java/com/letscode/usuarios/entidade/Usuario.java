package com.letscode.usuarios.entidade;

import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "NOME_DO_USUARIO", nullable = false)
  private String nome;
  @Column(length = 100)
  private int idade;
  @Column(unique = true)
  private String email;
  @Column(nullable = false)
  private String senha;
  @Column(nullable = false)
  private String cpf;

  private LocalDateTime dataCadastro;
  private LocalDateTime dataUltimaAtualizacao;

  public Usuario() {
  }

  public Usuario(Integer id, String nome, int idade, String email, String senha, String cpf, LocalDateTime dataCadastro) {
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.email = email;
    this.senha = senha;
    this.cpf = cpf;
    this.dataCadastro = dataCadastro;
    this.dataUltimaAtualizacao = LocalDateTime.now();
  }


}