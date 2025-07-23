package com.main.app_cotacao_v2.controller.escola;

import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.escola.EscolaDto;
import com.main.app_cotacao_v2.service.escola.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    //GET
    @GetMapping
    public List<Escola> getEscolas() {
        return escolaService.getEscolas();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Escola getEscolaById(@PathVariable Long id) {
        return escolaService.getEscolaById(id);
    }

    //POST
    @PostMapping
    public ResponseEntity<String> postEscola(@RequestBody EscolaDto dto) {
        return escolaService.postEscola(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEscolaById(@PathVariable Long id) {
        try {
            escolaService.deletarPorId(id);
            return new ResponseEntity<>("Escola deletada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar escola: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEscolaById(@PathVariable Long id, @RequestBody EscolaDto escolaDtoAtualizada) {
        try {
            escolaService.atualizarPorId(id, escolaDtoAtualizada);
            return new ResponseEntity<>("Escola atualizada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar escola: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
