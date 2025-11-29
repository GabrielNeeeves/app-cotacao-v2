package com.main.app_cotacao_v2.service.ofertaMaterialListaService;

import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterialResponseDto;
import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialLista;
import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialListaDto;
import com.main.app_cotacao_v2.model.ofertaMaterialLista.OfertaMaterialListaResponseDto;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import com.main.app_cotacao_v2.repository.ofertaMaterial.OfertaMaterialRepository;
import com.main.app_cotacao_v2.repository.ofertaMaterialListaRepository.OfertaMaterialListaRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfertaMaterialListaService {

    @Autowired
    private OfertaMaterialRepository ofertaRepository;
    @Autowired
    private OfertaMaterialListaRepository listaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<OfertaMaterialListaResponseDto> findAllWithDetails() {
        return listaRepository.findAll().stream().map(lista -> {
            List<OfertaMaterialResponseDto> ofertas = lista.getOfertaIds().stream()
                    .map(ofertaId -> ofertaRepository.findById(ofertaId)
                            .map(oferta -> new OfertaMaterialResponseDto(
                                    oferta.getId(),
                                    oferta.getMaterial().getNome(),
                                    oferta.getPreco(),
                                    oferta.getPrazoEntrega(),
                                    oferta.getObservacoes()
                            ))
                            .orElse(null)
                    )
                    .filter(o -> o != null)
                    .toList();

            return new OfertaMaterialListaResponseDto(lista.getId(), lista.getFuncionario_id(), ofertas);
        }).toList();
    }

    public OfertaMaterialListaResponseDto findByIdWithDetails(Long id) {
        OfertaMaterialLista lista = listaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        List<OfertaMaterialResponseDto> ofertas = lista.getOfertaIds().stream()
                .map(ofertaId -> ofertaRepository.findById(ofertaId)
                        .map(oferta -> new OfertaMaterialResponseDto(
                                oferta.getId(),
                                oferta.getMaterial().getNome(), // assumindo que Material tem "nome"
                                oferta.getPreco(),
                                oferta.getPrazoEntrega(),
                                oferta.getObservacoes()
                        ))
                        .orElse(null)
                )
                .filter(o -> o != null) // remove nulls caso algum ID não exista
                .collect(Collectors.toList());

        return new OfertaMaterialListaResponseDto(lista.getId(), lista.getFuncionario_id(), ofertas);
    }

    public OfertaMaterialLista save(OfertaMaterialListaDto dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + dto.funcionarioId()));

        OfertaMaterialLista lista = new OfertaMaterialLista();
        lista.setFuncionario_id(funcionario);   // seta a associação
        lista.setOfertaIds(dto.ofertaIds() != null ? dto.ofertaIds() : new ArrayList<>());
        return listaRepository.save(lista);
    }

    public void delete(Long id) {
        listaRepository.deleteById(id);
    }
}
