package com.main.app_cotacao_v2.service.usuariosService;

import com.main.app_cotacao_v2.repository.usuariosRepository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository adminRepo;

    public void cadastrarAdministrador(String nome, String email, String senha, String role) {
        adminRepo.cadastrarAdministrador(nome, email, senha, role);
    }

}
