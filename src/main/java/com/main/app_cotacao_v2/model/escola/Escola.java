package com.main.app_cotacao_v2.model.escola;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "escola")
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=200)
    private String nome;

    @Column(nullable=false, length=200)
    private String endereco;

    @Column(length=20)
    private String tipoEscola;  // "PUBLICA" ou "PRIVADA"

    @Column(length=20)
    private String cnpj;

    @Column(length=200)
    private String telefone;

    public Escola(EscolaDto dto) {
        nome = dto.nome();
        endereco = dto.endereco();
        cnpj = dto.cnpj();
        telefone = dto.telefone();
        tipoEscola = dto.tipoEscola();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoEscola() {
        return tipoEscola;
    }

    public void setTipoEscola(String tipoEscola) {
        this.tipoEscola = tipoEscola;
    }
}

