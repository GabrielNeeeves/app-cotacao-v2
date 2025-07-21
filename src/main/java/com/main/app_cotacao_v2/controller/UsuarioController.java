package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.dto.UsuarioDto;
import com.main.app_cotacao_v2.repository.usuariosRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    //GET
    @GetMapping
    public List<Usuario> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping
    public ResponseEntity<String> post(@RequestBody UsuarioDto dto) {
        try {
            Usuario novoUsuario = new Usuario(dto);
            repo.cadastrarUsuario(novoUsuario.getNome(), novoUsuario.getEmail(), novoUsuario.getSenha(), novoUsuario.getRole().name());
            return new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar usuário: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        try{
            Optional<Usuario> usuarioExiste = repo.findById(id);
            if(usuarioExiste.isPresent()) {
                Usuario user = usuarioExiste.get();
                user.setNome(dto.nome());
                user.setEmail(dto.email());
                user.setSenha(dto.senha());
                user.setRole(dto.role());
                repo.save(user);
            }
            return new ResponseEntity<>("Usuário atualizado com sucesso", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar o usuário: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("Usuário deletado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar o usuário: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
