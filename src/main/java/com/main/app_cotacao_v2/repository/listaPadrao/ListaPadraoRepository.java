package com.main.app_cotacao_v2.repository.listaPadrao;

import com.main.app_cotacao_v2.model.listaPadrao.ListaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ListaPadraoRepository extends JpaRepository<ListaPadrao, Long> {

    @Procedure(procedureName = "sp_cadastrar_lista_padrao")
    void cadastrarListaPadrao(
            @Param("p_funcionario_id") Long funcionario_id,
            @Param("p_escola_id") Long escola_id,
            @Param("p_ano_letivo") Integer ano_letivo,
            @Param("p_serie") String serie
    );
}
