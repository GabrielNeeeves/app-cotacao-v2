package com.main.app_cotacao_v2.service.usuariosService;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.usuariosModel.Cliente;
import com.main.app_cotacao_v2.model.usuariosModel.dto.ClienteDto;
import com.main.app_cotacao_v2.repository.alunoRepository.AlunoRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    public void cadastrarCliente(Integer alunoId, Integer usuarioId) {
        clienteRepo.cadastrarCliente(alunoId, usuarioId);
    }

    public void cadastrarClienteUsuarioAluno(String nome, String email, String senha, String alunoNome,
                                             String alunoSerie, String alunoTurno, Integer alunoAnoLetivo,
                                             String alunoObservacoes, Integer alunoEscolaId, String role) {
        clienteRepo.cadastrarClienteUsuarioAluno(
                nome, email, senha,
                alunoNome, alunoSerie, alunoTurno,
                alunoAnoLetivo, alunoObservacoes, alunoEscolaId, role
        );
    }

    //POST
    public ResponseEntity<String> cadastrarClienteComUsuario(ClienteDto dto) {
        try {
            if (dto.alunoId() != null) {
                Optional<Aluno> alunoExiste = alunoRepo.findById(dto.alunoId());
                if (alunoExiste.isEmpty()) {
                    throw new RuntimeException("Aluno com ID " + dto.alunoId() + " não foi encontrado");
                }
            }

            clienteRepo.cadastrarClienteComUsuario(dto.nome(), dto.email(), dto.senha(), dto.alunoId());
            return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //DELETE
    public void deleteClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepo.findById(id);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado");
        }
        clienteRepo.deleteById(id);
    }

}
