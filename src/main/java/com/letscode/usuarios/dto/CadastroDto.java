package com.letscode.usuarios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CadastroDto {
    // DATA TRANSFER OBJECT

    @JsonProperty("nome_do_usuario")
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private String cpf;

    private LocalDateTime dataCadastro;


    public CadastroDto(String nome, int idade, String email, String senha, String cpf, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }

}
