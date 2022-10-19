package com.letscode.usuarios.service;


import at.favre.lib.crypto.bcrypt.BCrypt;

import com.letscode.usuarios.dto.CadastroDto;
import com.letscode.usuarios.dto.GetUsuarioDto;
import com.letscode.usuarios.entidade.Usuario;
import com.letscode.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Integer criarNovoUsuario(CadastroDto dto) {
        String senhaCriptografada = BCrypt.withDefaults()
                .hashToString(12, dto.getSenha().toCharArray());

        Usuario novoUsuario = new Usuario(null,
                dto.getNome(),
                dto.getIdade(),
                dto.getEmail(),
                senhaCriptografada,
                dto.getCpf(), LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        return usuarioSalvo.getId();
    }

    public GetUsuarioDto buscarUsuarioPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        return new GetUsuarioDto(usuario);
    }

    public List<GetUsuarioDto> obterTodos() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();

        List<GetUsuarioDto> listaRetorno = new ArrayList<>();

        usuarios.forEach(usuario -> listaRetorno.add(new GetUsuarioDto(usuario)));

        return listaRetorno;
    }

    public void deletar(int id) {
        usuarioRepository.deleteById(id);
    }

    public void alterar(int id, CadastroDto dto) {

        Usuario usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Usuario usuarioAtualizado =
                alteraDados(
                        () -> Optional.ofNullable(dto.getNome()).orElseGet(usuarioEncontrado::getNome),
                () -> Optional.ofNullable(dto.getCpf()).orElseGet(usuarioEncontrado::getCpf),
                () -> Optional.ofNullable(dto.getIdade()).orElseGet(usuarioEncontrado::getIdade),
                () -> Optional.ofNullable(dto.getEmail()).orElseGet(usuarioEncontrado::getEmail),
                () -> Optional.ofNullable(dto.getSenha()).orElseGet(usuarioEncontrado::getSenha),
                usuarioEncontrado);


        usuarioRepository.save(usuarioAtualizado);

    }

    private Usuario alteraDados(Supplier<String> verificaNome,
                                Supplier<String> verificaCpf,
                                Supplier<Integer> verificaIdade,
                                Supplier<String> verificaEmail,
                                Supplier<String> verificaSenha,
                                Usuario usuario) {

        String nome = verificaNome.get();
        int idade = verificaIdade.get();
        String email = verificaEmail.get();
        String senha = verificaSenha.get();
        String cpf = verificaCpf.get();

        return new Usuario(usuario.getId(), nome, idade, email, senha, cpf, usuario.getDataCadastro());
    }
}
