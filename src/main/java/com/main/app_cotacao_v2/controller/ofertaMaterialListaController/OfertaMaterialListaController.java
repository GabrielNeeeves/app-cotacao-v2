package com.main.app_cotacao_v2.controller.ofertaMaterialListaController;

import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialLista;
import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialListaDto;
import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialListaResponseDto;
import com.main.app_cotacao_v2.service.ofertaMaterialListaService.OfertaMaterialListaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oferta_lista")
public class OfertaMaterialListaController {

    private final OfertaMaterialListaService service;

    public OfertaMaterialListaController(OfertaMaterialListaService service) {
        this.service = service;
    }

    @GetMapping
    public List<OfertaMaterialListaResponseDto> getAll() {
        return service.findAllWithDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaMaterialListaResponseDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findByIdWithDetails(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public OfertaMaterialLista create(@RequestBody OfertaMaterialLista lista) {
//        return service.save(lista);
//    }

    @PostMapping
    public ResponseEntity<OfertaMaterialLista> create(@RequestBody OfertaMaterialListaDto dto) {
        OfertaMaterialLista saved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
