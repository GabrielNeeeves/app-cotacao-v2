package com.main.app_cotacao_v2.service.usuariosService;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.aluno.AlunoDto;
import com.main.app_cotacao_v2.model.empresa.Empresa;
import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.model.usuariosModel.Funcionario;
import com.main.app_cotacao_v2.model.usuariosModel.FuncionarioView;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.dto.FuncionarioDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.FuncionarioPostDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.FuncionarioUpdateDto;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioViewRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    @Autowired
    private FuncionarioViewRepository funcionarioViewRepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET
    public List<FuncionarioView> getFuncionarios() {
        return funcionarioViewRepo.findAll();
    }

    //GET BY ID
    public FuncionarioView getFuncionarioById(Long id) {
        Optional<FuncionarioView> funcionarioExiste = funcionarioViewRepo.findById(id);
        if(funcionarioExiste.isEmpty()) {
            throw new RuntimeException("Funcionario com ID " + id + " não encontrado");
        }
        return funcionarioExiste.get();
    }

    //POST
    public ResponseEntity<String> postFuncionario(FuncionarioPostDto dto) {
        try {
            funcionarioRepo.cadastrarFuncionario(dto.nome(), dto.email(), dto.senha(), dto.salario(), dto.empresaId(), dto.escolaId());
            return new ResponseEntity<>("Funcionario cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar funcionario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    public void deletarPorId(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepo.findById(id);
        if (funcionario.isEmpty()) {
            throw new RuntimeException("Funcionario com ID " + id + " não foi encontrado");
        }
        funcionarioRepo.deleteById(id);
    }

    //PUT
    public ResponseEntity<?> atualizarFuncionario(Long id, FuncionarioUpdateDto dto) {
        Optional<Funcionario> optFuncionario = funcionarioRepo.findById(id);

        if (optFuncionario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario funcionario = optFuncionario.get();

        // Atualiza dados do usuário vinculado
        Usuario usuario = funcionario.getUsuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        usuarioRepository.save(usuario); // salva as alterações do usuário

        // Atualiza dados específicos do funcionário
        funcionario.setSalario(dto.salario());

//        if (dto.empresaId() != null) {
//            funcionario.setEmpresa(new Empresa(dto.empresaId()));
//            funcionario.setEscola(null);
//        } else if (dto.escolaId() != null) {
//            funcionario.setEscola(new Escola(dto.escolaId()));
//            funcionario.setEmpresa(null);
//        }

        funcionarioRepo.save(funcionario); // salva as alterações

        return ResponseEntity.ok().build();
    }

}
