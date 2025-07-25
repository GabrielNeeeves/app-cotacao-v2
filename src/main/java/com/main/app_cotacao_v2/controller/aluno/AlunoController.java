package com.main.app_cotacao_v2.controller.aluno;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.aluno.AlunoDto;
import com.main.app_cotacao_v2.service.aluno.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    //GET
    @GetMapping
    public List<Aluno> getAlunos() {
        return alunoService.getAlunos();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return alunoService.getAlunoById(id);
    }

    //POST
    @PostMapping
    public ResponseEntity<String> postAluno(@RequestBody AlunoDto dto) {
        return alunoService.postAluno(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlunoById(@PathVariable Long id) {
        try {
            alunoService.deletarPorId(id);
            return new ResponseEntity<>("Aluno deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar aluno: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlunoById(@PathVariable Long id, @RequestBody AlunoDto alunoDtoAtualizado) {
        try {
            alunoService.atualizarPorId(id, alunoDtoAtualizado);
            return new ResponseEntity<>("Aluno atualizado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar aluno: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

}
