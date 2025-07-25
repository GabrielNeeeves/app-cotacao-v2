package com.main.app_cotacao_v2.controller.usuarios;

import com.main.app_cotacao_v2.model.usuariosModel.FuncionarioView;
import com.main.app_cotacao_v2.model.usuariosModel.dto.ClienteDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.FuncionarioPostDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.FuncionarioUpdateDto;
import com.main.app_cotacao_v2.service.usuariosService.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    //GET
    @GetMapping
    public List<FuncionarioView> getFuncionarios() {
        return funcionarioService.getFuncionarios();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioView> getFuncionarioById(@PathVariable Long id) {
        try {
            FuncionarioView funcionario = funcionarioService.getFuncionarioById(id);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody FuncionarioPostDto dto) {
        return funcionarioService.postFuncionario(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long id) {
        try {
            funcionarioService.deletarPorId(id);
            return new ResponseEntity<>("Funcionario deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar funcionario: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioUpdateDto dto
    ) {
        return funcionarioService.atualizarFuncionario(id, dto);
    }

}
