package com.main.app_cotacao_v2.model.ofertaMaterialLista;

import com.main.app_cotacao_v2.model.ofertaMaterial.OfertaMaterialResponseDto;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;

import java.util.List;

public record OfertaMaterialListaResponseDto(
        Long id,
        Funcionario funcionario,
        List<OfertaMaterialResponseDto> ofertas
) {}
