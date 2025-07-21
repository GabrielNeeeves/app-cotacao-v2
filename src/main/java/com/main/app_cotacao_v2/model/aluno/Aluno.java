package com.main.app_cotacao_v2.model.aluno;

import com.main.app_cotacao_v2.model.escola.Escola;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @Column(nullable = false, length=200)
    private String nome;

    @Column(nullable = false, length=50)
    private String serie;

    @Column(length=20)
    private String turno;  // "MATUTINO", "VESPERTINO", "NOTURNO"

    @Column(nullable = false)
    private Integer anoLetivo;

    @Column(length=200)
    private String observacoes;
}
