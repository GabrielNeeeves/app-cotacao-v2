package com.main.app_cotacao_v2.controller.material;

import com.main.app_cotacao_v2.model.material.Material;
import com.main.app_cotacao_v2.model.material.MaterialDto;
import com.main.app_cotacao_v2.repository.material.MaterialRepository;
import com.main.app_cotacao_v2.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    private MaterialService service;

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping
    public List<Material> getAll() {
        return service.findAllMaterial();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getById(@PathVariable Long id) {
        return service.findMaterialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MaterialDto materialDto) {
        return service.saveMaterial(materialDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> update(@PathVariable Long id, @RequestBody MaterialDto materialDto) {
        Optional<Material> optMaterial = service.findMaterialById(id);
        if(optMaterial.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Material material = optMaterial.get();
        material.setNome(materialDto.nome());
        material.setDescricao(materialDto.descricao());
        material.setCategoria(materialDto.categoria());
        materialRepository.save(material);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findMaterialById(id)
                .map(existing -> {
                    service.deleteMaterialById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
