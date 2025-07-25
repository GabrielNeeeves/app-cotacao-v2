package com.main.app_cotacao_v2.repository.usuariosRepository;

import com.main.app_cotacao_v2.model.usuariosModel.FuncionarioView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioViewRepository extends JpaRepository<FuncionarioView, Long> {
}
