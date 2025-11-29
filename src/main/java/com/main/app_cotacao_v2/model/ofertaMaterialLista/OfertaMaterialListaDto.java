package com.main.app_cotacao_v2.model.ofertaMaterialLista;

import java.util.List;

public record OfertaMaterialListaDto(
        Long funcionarioId,
        List<Long> ofertaIds
) {
}
