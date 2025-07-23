package com.main.app_cotacao_v2.repository.alunoRepository;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Procedure(procedureName = "sp_cadastrar_aluno")
    void cadastrarAluno(
            @Param("p_escola_id") Long escola_id,
            @Param("p_nome") String nome,
            @Param("p_serie") String serie,
            @Param("p_turno") String turno,                         // "MATUTINO", "VESPERTINO", "NOTURNO"
            @Param("p_ano_letivo") Integer ano_letivo,
            @Param("p_observacoes") String observacoes
    );
}
