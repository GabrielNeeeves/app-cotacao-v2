package com.main.app_cotacao_v2.model.usuariosModel;

import com.main.app_cotacao_v2.model.usuariosModel.dto.UsuarioDto;
import com.main.app_cotacao_v2.model.usuariosModel.roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
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

    public Usuario(String nome, String email, String senha, Roles role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Roles.ADMINISTRADOR) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"),
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        else if (this.role == Roles.FUNCIONARIO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}