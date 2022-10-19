package com.letscode.usuarios.dto;

import com.letscode.usuarios.entidade.Usuario;

import lombok.Getter;

@Getter
public class GetUsuarioDto {
    private String nome;
    private String email;
    private String cpf;

    public GetUsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
    }

}
