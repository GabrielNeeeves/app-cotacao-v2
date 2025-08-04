package com.main.app_cotacao_v2.service.ofertaMaterial;

import com.main.app_cotacao_v2.model.material.Material;
import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterial;
import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterialDto;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import com.main.app_cotacao_v2.repository.material.MaterialRepository;
import com.main.app_cotacao_v2.repository.ofertaMaterial.OfertaMaterialRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaMaterialService {

    @Autowired
    private OfertaMaterialRepository ofertaMaterialRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public List<OfertaMaterial> findAllOfertaMaterial() {
        return ofertaMaterialRepository.findAll();
    }

    public Optional<OfertaMaterial> findOfertaMateriaById(Long id) {
        return ofertaMaterialRepository.findById(id);
    }

    public OfertaMaterial criarOferta(OfertaMaterialDto dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        Material material = materialRepository.findById(dto.materialId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado"));

        OfertaMaterial oferta = new OfertaMaterial(dto, funcionario, material);
        return ofertaMaterialRepository.save(oferta);

    }

    public void deleteOfertaMaterialById(Long id) {
        ofertaMaterialRepository.deleteById(id);
    }

    public OfertaMaterial atualizarOferta(Long id, OfertaMaterialDto dto) {
        OfertaMaterial oferta = ofertaMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oferta não encontrada"));

        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        Material material = materialRepository.findById(dto.materialId())
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));

        // Atualiza os campos
        oferta.setFuncionario(funcionario);
        oferta.setMaterial(material);
        oferta.setPreco(dto.preco());
        oferta.setPrazoEntrega(dto.prazoEntrega());
        oferta.setQuantidadeMinima(dto.quantidadeMinima() != null ? dto.quantidadeMinima() : 1);
        oferta.setObservacoes(dto.observacoes());

        return ofertaMaterialRepository.save(oferta);
    }

}
