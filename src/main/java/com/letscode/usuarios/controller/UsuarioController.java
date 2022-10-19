package com.letscode.usuarios.controller;

import com.letscode.usuarios.dto.CadastroDto;
import com.letscode.usuarios.dto.GetUsuarioDto;
import com.letscode.usuarios.entidade.Usuario;
import com.letscode.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {



    private static final List<Usuario> usuarios = new ArrayList<>();

    // buscar usuario por id
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public GetUsuarioDto buscarUsuario(@PathVariable int id) {

        GetUsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);

        return usuario;
    }

    // cadastrar novo usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer cadastrarNovo(@RequestBody CadastroDto dto) {
        // adicionar mais um no id

        Integer novoId = usuarioService.criarNovoUsuario(dto);

        return novoId;
    }

    // buscar todos os usuários
    @GetMapping
    public List<GetUsuarioDto> obterTodos() {

        List<GetUsuarioDto> getUsuarioDTOList = usuarioService.obterTodos();

        return getUsuarioDTOList;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterar (@PathVariable int id, @RequestBody CadastroDto dto){

        usuarioService.alterar(id, dto);

        return ResponseEntity.ok().build();
    }



}
