package com.main.app_cotacao_v2.service.empresa;

import com.main.app_cotacao_v2.model.empresa.Empresa;
import com.main.app_cotacao_v2.model.empresa.EmpresaDto;
import com.main.app_cotacao_v2.repository.empresaRepository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepo;

    //GET
    public List<Empresa> getEmpresas() {
        return empresaRepo.findAll();
    }

    //GET BY ID
    public Empresa getEmpresaById(Long id) {
        Optional<Empresa> empresaExiste = empresaRepo.findById(id);
        if(empresaExiste.isEmpty()) {
            throw new RuntimeException("Empresa com ID " + id + " não encontrada");
        }
        return empresaExiste.get();
    }

    //POST
    public ResponseEntity<String> createEmpresa(EmpresaDto dto) {
        try {
            Empresa novaEmpresa = new Empresa(dto);
            empresaRepo.save(novaEmpresa);
            return new ResponseEntity<>("Empresa cadastrada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar empresa: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    public void deleteEmpresaById(Long id) {
        Optional<Empresa> empresa = empresaRepo.findById(id);
        if (empresa.isEmpty()) {
            throw new RuntimeException("Empresa com ID " + id + " não encontrada");
        }
        empresaRepo.deleteById(id);
    }

    //PUT
    public void atualizarEmpresaPorId(Long id, EmpresaDto empresaDtoAtualizada) {
        Optional<Empresa> empresaExiste = empresaRepo.findById(id);
        if (empresaExiste.isEmpty()) {
            throw new RuntimeException("Empresa com ID " + id + " não encontrada");
        }
        Empresa empresa = empresaExiste.get();

        empresa.setNome(empresaDtoAtualizada.nome());
        empresa.setEndereco(empresaDtoAtualizada.endereco());
        empresa.setCnpj(empresaDtoAtualizada.cnpj());

        empresaRepo.save(empresa);
    }
}
