package com.main.app_cotacao_v2.repository.empresaRepository;

import com.main.app_cotacao_v2.model.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
