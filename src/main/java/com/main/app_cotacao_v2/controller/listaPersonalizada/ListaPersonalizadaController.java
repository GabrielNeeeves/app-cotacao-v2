package com.main.app_cotacao_v2.controller.listaPersonalizada;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoDto;
import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizadaDto;
import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizadaView;
import com.main.app_cotacao_v2.service.listaPersonalizada.ListaPersonalizadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas_personalizada")
public class ListaPersonalizadaController {

    @Autowired
    private ListaPersonalizadaService listaPersonalizadaService;

    //GET
    @GetMapping
    public List<ListaPersonalizadaView> getLista() {
        return listaPersonalizadaService.getListasPersonalizada();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ListaPersonalizadaView> getListaById(@PathVariable Long id) {
        try {
            ListaPersonalizadaView listaPersonalizadaView = listaPersonalizadaService.getById(id);
            return ResponseEntity.ok(listaPersonalizadaView);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping
    public void postListaPerson(@RequestBody ListaPersonalizadaDto dto) {
        listaPersonalizadaService.postListaPerson(dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListaPersonById(@PathVariable Long id) {
        try {
            listaPersonalizadaService.deleteListaPersonById(id);
            return new ResponseEntity<>("Lista Personalizada deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar Lista Personalizada: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateListaPerson(@PathVariable Long id, @RequestBody ListaPersonalizadaDto dto) {
        return listaPersonalizadaService.putListaPerson(id, dto);
    }

}
