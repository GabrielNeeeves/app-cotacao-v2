package com.main.app_cotacao_v2.service.listaPadrao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoDto;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoView;
import com.main.app_cotacao_v2.repository.listaPadrao.ListaPadraoRepository;
import com.main.app_cotacao_v2.repository.listaPadrao.ListaPadraoViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaPadraoService {

    @Autowired
    private ListaPadraoRepository listaPadraoRepository;

    @Autowired
    private ListaPadraoViewRepository listaPadraoViewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //GET
    public List<ListaPadraoView> getListaPadrao() {
        return listaPadraoViewRepository.findAll();
    }

    //GET BY ID
    public ListaPadraoView getListaPadraoById(Long id) {
        Optional<ListaPadraoView> optListaPadraoView = listaPadraoViewRepository.findById(id);
        if(optListaPadraoView.isEmpty()) {
            throw new RuntimeException("Lista Padrão com ID " + id + " não encontrada");
        }
        return optListaPadraoView.get();
    }

    //POST
    public void postListaPadrao(ListaPadraoDto dto) {
        try {
            String materiaisJson = objectMapper.writeValueAsString(dto.materiais());
            listaPadraoRepository.cadastrarListaPadrao(
                    dto.funcionario_id(),
                    dto.escola_id(),
                    dto.ano_letivo(),
                    dto.serie(),
                    materiaisJson
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar materiais para JSON", e);
        }
    }

    //DELETE
    public void deleteListaPadraoById(Long id) {
        Optional<ListaPadrao> optListaPadrao = listaPadraoRepository.findById(id);
        if(optListaPadrao.isEmpty()) {
            throw new RuntimeException("Lista Padrão com ID " + id + " não encontrada");
        }
        listaPadraoRepository.deleteById(id);
    }

    //PUT
//    public ResponseEntity<?> putListaPadrao(Long id, ListaPadraoDto dtoAtualizado) {
//        Optional<ListaPadrao> optListaPadrao = listaPadraoRepository.findById(id);
//        if(optListaPadrao.isEmpty()) {
//            throw new RuntimeException("Lista Padrão com ID " + id + " encontrada");
//        }
//
//        ListaPadrao listaPadrao = optListaPadrao.get();
//        listaPadrao.setSerie(dtoAtualizado.serie());
//        listaPadrao.setAno_letivo(dtoAtualizado.ano_letivo());
//        listaPadrao.setMateriais(dtoAtualizado.materiais());
//
//        listaPadraoRepository.save(listaPadrao);
//        return ResponseEntity.ok().build();
//    }

    //PUT GEMINI
    public ResponseEntity<?> putListaPadrao(Long id, ListaPadraoDto dtoAtualizado) {
        Optional<ListaPadrao> optListaPadrao = listaPadraoRepository.findById(id);
        if(optListaPadrao.isEmpty()) {
            throw new RuntimeException("Lista Padrão com ID " + id + " não encontrada");
        }

        ListaPadrao listaPadrao = optListaPadrao.get();
        listaPadrao.setSerie(dtoAtualizado.serie());
        listaPadrao.setAno_letivo(dtoAtualizado.ano_letivo()); // Usar anoLetivo
        listaPadrao.setMateriais(dtoAtualizado.materiais()); // Seta a List<MaterialDto> diretamente

        // Adicione a lógica para buscar e setar Funcionario e Escola se for o caso
        // Exemplo:
        // listaPadrao.setFuncionario(funcionarioRepository.findById(dtoAtualizado.funcionario_id()).orElseThrow(() -> new RuntimeException("Funcionario não encontrado")));
        // listaPadrao.setEscola(escolaRepository.findById(dtoAtualizado.escola_id()).orElseThrow(() -> new RuntimeException("Escola não encontrada")));


        listaPadraoRepository.save(listaPadrao);
        return ResponseEntity.ok().build();
    }

}
