package com.main.app_cotacao_v2.service.listaPersonalizada;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoDto;
import com.main.app_cotacao_v2.model.listaPadrao.ListaPadraoView;
import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizada;
import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizadaDto;
import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizadaView;
import com.main.app_cotacao_v2.repository.listaPersonalizada.ListaPersonalizadaRepository;
import com.main.app_cotacao_v2.repository.listaPersonalizada.ListaPersonalizadaViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaPersonalizadaService {

    @Autowired
    private ListaPersonalizadaRepository listaPersonalizadaRepository;

    @Autowired
    private ListaPersonalizadaViewRepository listaPersonalizadaViewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //GET
    public List<ListaPersonalizadaView> getListasPersonalizada() {
        return listaPersonalizadaViewRepository.findAll();
    }

    //GET BY ID
    public ListaPersonalizadaView getById(Long id) {
        Optional<ListaPersonalizadaView> optListaPersonView = listaPersonalizadaViewRepository.findById(id);
        if(optListaPersonView.isEmpty()) {
            throw new RuntimeException("Lista Personalizada com ID " + id + " não encontrada");
        }
        return optListaPersonView.get();
    }

    //POST
    public void postListaPerson(ListaPersonalizadaDto dto) {
        try {
            String materiaisJson = objectMapper.writeValueAsString(dto.materiais());
            listaPersonalizadaRepository.cadastrarListaPersonalizada(
                    dto.clienteId(),
                    dto.alunoId(),
                    dto.listaPadraoId(),
                    materiaisJson
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar materiais para JSON", e);
        }
    }

    //DELETE
    public void deleteListaPersonById(Long id) {
        Optional<ListaPersonalizada> optListaPerson = listaPersonalizadaRepository.findById(id);
        if(optListaPerson.isEmpty()) {
            throw new RuntimeException("Lista Personalizada com ID " + id + " não encontrada");
        }
        listaPersonalizadaRepository.deleteById(id);
    }

    //PUT
    public ResponseEntity<?> putListaPerson(Long id, ListaPersonalizadaDto dtoAtualizado) {
        Optional<ListaPersonalizada> optListaPerson = listaPersonalizadaRepository.findById(id);
        if(optListaPerson.isEmpty()) {
            throw new RuntimeException("Lista Personalizada com ID " + id + " não encontrada");
        }

        ListaPersonalizada listaPerson = optListaPerson.get();
        listaPerson.setMateriais(dtoAtualizado.materiais());

        listaPersonalizadaRepository.save(listaPerson);
        return ResponseEntity.ok().build();
    }

}
