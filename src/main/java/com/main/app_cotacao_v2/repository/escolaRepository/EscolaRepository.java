package com.main.app_cotacao_v2.repository.escolaRepository;

import com.main.app_cotacao_v2.model.escola.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface EscolaRepository extends JpaRepository<Escola, Long> {

    @Procedure(procedureName = "sp_cadastrar_escola")
    void cadatrarEscola(
      @Param("p_nome") String nome,
      @Param("p_endereco") String endereco,
      @Param("p_tipo_escola") String tipo_escola, // PUBLICA, PRIVADA
      @Param("p_cnpj") String cnpj,
      @Param("p_telefone") String telefone
    );
}
