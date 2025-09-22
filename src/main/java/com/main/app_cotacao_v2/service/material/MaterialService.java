package com.main.app_cotacao_v2.service.material;

import com.main.app_cotacao_v2.model.material.Material;
import com.main.app_cotacao_v2.model.material.MaterialDto;
import com.main.app_cotacao_v2.repository.material.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAllMaterial() {
        return materialRepository.findAll();
    }

    public Optional<Material> findMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    public ResponseEntity<String> saveMaterial(MaterialDto materialDto) {

        Material material = new Material(materialDto);

        materialRepository.save(material);
        return new ResponseEntity<>("Material cadastrado", HttpStatus.CREATED);
    }

    public void deleteMaterialById(Long id) {
        materialRepository.deleteById(id);
    }

}
