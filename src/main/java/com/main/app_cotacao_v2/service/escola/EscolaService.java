package com.main.app_cotacao_v2.service.escola;

import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.escola.EscolaDto;
import com.main.app_cotacao_v2.repository.escolaRepository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepo;

    //GET
    public List<Escola> getEscolas() {
        return escolaRepo.findAll();
    }

    //GET BY ID
    public Escola getEscolaById(Long id) {
        Optional<Escola> escolaExiste = escolaRepo.findById(id);
        if(escolaExiste.isEmpty()) {
            throw new RuntimeException("Escola com ID " + id + " não encontrada");
        }
        return escolaExiste.get();
    }

    //POST
    public ResponseEntity<String> postEscola(EscolaDto dto) {
        try {
            escolaRepo.cadatrarEscola(
                    dto.nome(),
                    dto.endereco(),
                    dto.tipoEscola(),
                    dto.cnpj(),
                    dto.telefone()
            );
            return new ResponseEntity<>("Escola cadastrada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar o escola: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    public void deletarPorId(Long id) {
        Optional<Escola> escola = escolaRepo.findById(id);
        if (escola.isEmpty()) {
            throw new RuntimeException("Escola com ID " + id + " não foi encontrada");
        }
        escolaRepo.deleteById(id);
    }

    //PUT
    public void atualizarPorId(Long id, EscolaDto escolaDtoAtualizada) {
        Optional<Escola> escolaExiste = escolaRepo.findById(id);
        if (escolaExiste.isEmpty()) {
            throw new RuntimeException("Escola com ID " + id + " não foi encontrada");
        }
        Escola escola = escolaExiste.get();
        escola.setNome(escolaDtoAtualizada.nome());
        escola.setEndereco(escolaDtoAtualizada.endereco());
        escola.setTipoEscola(escolaDtoAtualizada.tipoEscola());
        escola.setCnpj(escolaDtoAtualizada.cnpj());
        escola.setTelefone(escolaDtoAtualizada.telefone());

        escolaRepo.save(escola);
    }

}
