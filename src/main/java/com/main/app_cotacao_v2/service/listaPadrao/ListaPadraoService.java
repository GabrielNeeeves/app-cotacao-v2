package com.main.app_cotacao_v2.service.listaPadrao;

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

    @Autowired ListaPadraoViewRepository listaPadraoViewRepository;

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
    public ResponseEntity<String> postListaPadrao(ListaPadraoDto dto) {
        try {
            listaPadraoRepository.cadastrarListaPadrao(dto.funcionario_id(), dto.escola_id(), dto.ano_letivo(), dto.serie());
            return new ResponseEntity<>("Lista Padrão cadastrada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar Lista Padrão: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<?> putListaPadrao(Long id, ListaPadraoDto dtoAtualizado) {
        Optional<ListaPadrao> optListaPadrao = listaPadraoRepository.findById(id);
        if(optListaPadrao.isEmpty()) {
            throw new RuntimeException("Lista Padrão com ID " + id + " encontrada");
        }

        ListaPadrao listaPadrao = optListaPadrao.get();
        listaPadrao.setSerie(dtoAtualizado.serie());
        listaPadrao.setAno_letivo(dtoAtualizado.ano_letivo());

        listaPadraoRepository.save(listaPadrao);
        return ResponseEntity.ok().build();
    }

}
