package com.main.app_cotacao_v2.repository.material;

import com.main.app_cotacao_v2.model.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
