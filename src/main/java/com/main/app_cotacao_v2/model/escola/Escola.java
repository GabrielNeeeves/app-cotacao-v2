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
    private String cnpj;

    @Column(length=200)
    private String telefone;

    @Column(length=20)
    private String tipoEscola;  // "PUBLICA" ou "PRIVADA"
}

