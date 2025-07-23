package com.main.app_cotacao_v2.model.usuariosModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_cliente")
public class ClienteView {

    @Id
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "aluno_id")
    private Long alunoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nome;
    private String email;
    private String senha;
    private String role;

    // getters e setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
