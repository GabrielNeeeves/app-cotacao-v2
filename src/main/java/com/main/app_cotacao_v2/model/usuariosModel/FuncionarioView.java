package com.main.app_cotacao_v2.model.usuariosModel;

import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "vw_funcionario")
public class FuncionarioView {

    @Id
    @Column(name = "funcionario_id")
    private Long funcionario_id;

    private BigDecimal salario;

    @Column(name = "empresa_id")
    private Long empresa_id;

    @Column(name = "escola_id")
    private Long escola_id;

    @Column(name = "usuario_id")
    private Long usuario_id;

    private String usuario_nome;
    private String usuario_email;
    private String usuario_senha;
    private String usuario_role;

    public Long getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Long funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(Long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public Long getEscola_id() {
        return escola_id;
    }

    public void setEscola_id(Long escola_id) {
        this.escola_id = escola_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_senha() {
        return usuario_senha;
    }

    public void setUsuario_senha(String usuario_senha) {
        this.usuario_senha = usuario_senha;
    }

    public String getUsuario_role() {
        return usuario_role;
    }

    public void setUsuario_role(String usuario_role) {
        this.usuario_role = usuario_role;
    }
}
