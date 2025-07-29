package com.main.app_cotacao_v2.controller.listaPadrao;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoDto;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoView;
import com.main.app_cotacao_v2.service.listaPadrao.ListaPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas_padrao")
public class ListaPadraoController {

    @Autowired
    private ListaPadraoService listaPadraoService;

    //GET
    @GetMapping
    public List<ListaPadraoView> getAllLIstaPadrao() {
        return listaPadraoService.getListaPadrao();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ListaPadraoView> getListaPadraoById(@PathVariable Long id) {
        try {
            ListaPadraoView listaPadraoView = listaPadraoService.getListaPadraoById(id);
            return ResponseEntity.ok(listaPadraoView);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping
    public void postListaPadrao(@RequestBody ListaPadraoDto dto) {
        listaPadraoService.postListaPadrao(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListaPadraoById(@PathVariable Long id) {
        try {
            listaPadraoService.deleteListaPadraoById(id);
            return new ResponseEntity<>("Lista Padrão deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar Lista Padrão: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateListaPadrao(@PathVariable Long id, @RequestBody ListaPadraoDto dto) {
        return listaPadraoService.putListaPadrao(id, dto);
    }

}
