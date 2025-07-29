package com.main.app_cotacao_v2.model.listaPersonalizada;

public record ListaPersonalizadaDto(
        Long clienteId,
        Long alunoId,
        Long listaPadraoId,
        String materiais // JSON em String
) {
}
