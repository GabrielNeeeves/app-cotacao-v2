package com.main.app_cotacao_v2.model.usuariosModel;

import com.main.app_cotacao_v2.model.usuariosModel.dto.UsuarioDto;
import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String nome;

    @Column(nullable = false, unique = true, length=100)
    private String email;

    @Column(nullable = false, length=100)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    public Usuario(UsuarioDto dto) {
        nome = dto.nome();
        email = dto.email();
        senha = dto.senha();
        role = dto.role();
    }

    public Long getId() {
        return id;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}