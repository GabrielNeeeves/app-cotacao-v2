package com.main.app_cotacao_v2.model.listaPersonalizada;

import com.main.app_cotacao_v2.model.material.MaterialDto;
import com.main.app_cotacao_v2.model.material.MaterialPadraoDto;

import java.util.List;

public record ListaPersonalizadaDto(
        Long clienteId,
        Long alunoId,
        Long listaPadraoId,
        List<MaterialDto> materiais // JSON em String
) {
}
