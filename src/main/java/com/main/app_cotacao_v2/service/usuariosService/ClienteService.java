package com.main.app_cotacao_v2.service.usuariosService;

import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    // Chama a procedure simples (já tem os IDs)
    public void cadastrarCliente(Integer alunoId, Integer usuarioId) {
        clienteRepo.cadastrarCliente(alunoId, usuarioId);
    }

    // Chama a procedure completa (cria tudo)
    public void cadastrarClienteUsuarioAluno(String nome, String email, String senha, String alunoNome,
                                             String alunoSerie, String alunoTurno, Integer alunoAnoLetivo,
                                             String alunoObservacoes, Integer alunoEscolaId, String role) {
        clienteRepo.cadastrarClienteUsuarioAluno(
                nome, email, senha,
                alunoNome, alunoSerie, alunoTurno,
                alunoAnoLetivo, alunoObservacoes, alunoEscolaId, role
        );
    }

}
