package com.main.app_cotacao_v2.repository.listaPersonalizada;

import com.main.app_cotacao_v2.model.listaPersonalizada.ListaPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ListaPersonalizadaRepository extends JpaRepository<ListaPersonalizada, Long> {

    @Procedure(procedureName = "sp_cadastrar_lista_personalizada")
    void cadastrarListaPersonalizada(
            @Param("p_cliente_id") Long cliente_id,
            @Param("p_aluno_id") Long aluno_id,
            @Param("p_lista_padrao_id") Long lista_padrao_id,
            @Param("p_materiais") String materiais
    );

}
