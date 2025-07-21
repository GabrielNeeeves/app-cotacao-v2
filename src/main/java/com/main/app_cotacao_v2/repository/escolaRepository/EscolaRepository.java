package com.main.app_cotacao_v2.repository.escolaRepository;

import com.main.app_cotacao_v2.model.escola.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
}
