package com.main.app_cotacao_v2.repository.usuariosRepository;

import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Procedure(procedureName = "sp_cadastrar_usuario")
    void cadastrarUsuario(
            @Param("p_nome") String nome,
            @Param("p_email") String email,
            @Param("p_senha") String senha,
            @Param("p_role") String role
    );

}
