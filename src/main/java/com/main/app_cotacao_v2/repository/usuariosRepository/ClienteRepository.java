package com.main.app_cotacao_v2.repository.usuariosRepository;

import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Procedure simples: recebe os IDs
    @Procedure(procedureName = "sp_cadastrar_cliente")
    void cadastrarCliente(
            @Param("p_aluno_id") Integer alunoId,
            @Param("p_usuario_id") Integer usuarioId
    );

    // Procedure completa: cria usuario + aluno + cliente
    @Procedure(procedureName = "sp_cadastrar_cliente_usuario_aluno")
    void cadastrarClienteUsuarioAluno(
            @Param("p_nome") String nome,
            @Param("p_email") String email,
            @Param("p_senha") String senha,
            @Param("p_aluno_nome") String alunoNome,
            @Param("p_aluno_serie") String alunoSerie,
            @Param("p_aluno_turno") String alunoTurno,
            @Param("p_aluno_ano_letivo") Integer alunoAnoLetivo,
            @Param("p_aluno_observacoes") String alunoObservacoes,
            @Param("p_aluno_escola_id") Integer alunoEscolaId,
            @Param("p_role") String role
    );
    @Procedure(procedureName = "sp_cadastrar_cliente_com_usuario")
    void cadastrarClienteComUsuario(
            @Param("p_nome") String nome,
            @Param("p_email") String email,
            @Param("p_senha") String senha,
            @Param("p_aluno_id") Long alunoId
    );

}
