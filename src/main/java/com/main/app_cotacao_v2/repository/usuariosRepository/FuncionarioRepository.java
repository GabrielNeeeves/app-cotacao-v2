package com.main.app_cotacao_v2.repository.usuariosRepository;

import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Procedure(procedureName = "sp_cadastrar_funcionario")
    void cadastrarFuncionario(
            @Param("p_nome") String nome,
            @Param("p_email") String email,
            @Param("p_senha") String senha,
            @Param("p_salario") BigDecimal salario,
            @Param("p_empresa_id") Integer empresaId,
            @Param("p_escola_id") Integer escolaId
    );

    Optional<Funcionario> findByUsuarioId(Long usuarioId);

}
