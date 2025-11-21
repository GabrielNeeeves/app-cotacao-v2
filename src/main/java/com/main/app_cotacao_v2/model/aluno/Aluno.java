package com.main.app_cotacao_v2.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import com.main.app_cotacao_v2.repository.escolaRepository.EscolaRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

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

    public Aluno(
            AlunoDto dto,
            EscolaRepository escolaRepository,
            ClienteRepository clienteRepository
    ) {
        // ✅ Carrega cliente
        this.cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente não encontrado com ID: " + dto.clienteId()
                ));

        // ✅ Carrega escola
        this.escola = escolaRepository.findById(dto.escolaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Escola não encontrada com ID: " + dto.escolaId()
                ));

        // ✅ Copia dados simples
        this.nome = dto.nome();
        this.serie = dto.serie();
        this.turno = dto.turno();
        this.anoLetivo = dto.anoLetivo();
        this.observacoes = dto.observacoes();
    }

    public Long getId() {
        return id;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
