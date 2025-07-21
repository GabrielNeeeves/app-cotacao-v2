package com.main.app_cotacao_v2.repository.alunoRepository;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
