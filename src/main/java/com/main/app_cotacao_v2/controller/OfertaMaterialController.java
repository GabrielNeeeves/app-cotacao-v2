package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterial;
import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterialDto;
import com.main.app_cotacao_v2.service.ofertaMaterial.OfertaMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ofertas")
public class OfertaMaterialController {

    @Autowired
    private OfertaMaterialService ofertaService;

    // GET
    @GetMapping
    public List<OfertaMaterial> get() {
        return ofertaService.findAllOfertaMaterial();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OfertaMaterial> getOfertaById(@PathVariable Long id) {
        return ofertaService.findOfertaMateriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public OfertaMaterial createOferta(@RequestBody OfertaMaterialDto dto) {
        return ofertaService.criarOferta(dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOfertaById(@PathVariable Long id) {
        try {
            ofertaService.deleteOfertaMaterialById(id);
            return new ResponseEntity<>("Oferta deletada", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar oferta: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOferta(@PathVariable Long id, @RequestBody OfertaMaterialDto dto) {
        try {
            ofertaService.atualizarOferta(id, dto);
            return new ResponseEntity<>("Oferta atualizada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar oferta: " + e.getMessage(),
                HttpStatus.NOT_FOUND);
        }
    }
}
