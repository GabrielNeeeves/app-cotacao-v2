package com.main.app_cotacao_v2.controller.empresa;

import com.main.app_cotacao_v2.model.aluno.AlunoDto;
import com.main.app_cotacao_v2.model.empresa.Empresa;
import com.main.app_cotacao_v2.model.empresa.EmpresaDto;
import com.main.app_cotacao_v2.service.empresa.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    //GET
    @GetMapping
    public List<Empresa> getEmpresas() {
        return empresaService.getEmpresas();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id);
    }

    //POST
    @PostMapping
    public ResponseEntity<String> postEmpresa(@RequestBody EmpresaDto dto) {
        return empresaService.createEmpresa(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpresaById(@PathVariable Long id) {
        try {
            empresaService.deleteEmpresaById(id);
            return new ResponseEntity<>("Empresa deletada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar empresa: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmpresaById(@PathVariable Long id, @RequestBody EmpresaDto empresaDtoAtualizada) {
        try {
            empresaService.atualizarEmpresaPorId(id, empresaDtoAtualizada);
            return new ResponseEntity<>("Empresa atualizado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar empresa: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

}
