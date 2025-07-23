package com.main.app_cotacao_v2.service.aluno;

import com.main.app_cotacao_v2.model.aluno.Aluno;
import com.main.app_cotacao_v2.model.aluno.AlunoDto;
import com.main.app_cotacao_v2.model.escola.Escola;
import com.main.app_cotacao_v2.repository.alunoRepository.AlunoRepository;
import com.main.app_cotacao_v2.repository.escolaRepository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private EscolaRepository escolaRepo;

    //GET
    public List<Aluno> getAlunos() {
        return alunoRepo.findAll();
    }

    //GET BY ID
    public Aluno getAlunoById(Long id) {
        Optional<Aluno> alunoExiste = alunoRepo.findById(id);
        if(alunoExiste.isEmpty()) {
            throw new RuntimeException("Aluno com ID " + id + " não foi encontrado");
        }
        return alunoExiste.get();
    }

    //POST
    public ResponseEntity<String> postAluno(AlunoDto dto) {
        try {
            alunoRepo.cadastrarAluno(
                    dto.escolaId(),
                    dto.nome(),
                    dto.serie(),
                    dto.turno(),
                    dto.anoLetivo(),
                    dto.observacoes()
            );
            return new ResponseEntity<>("Aluno cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar aluno: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    public void deletarPorId(Long id) {
        Optional<Aluno> aluno = alunoRepo.findById(id);
        if (aluno.isEmpty()) {
            throw new RuntimeException("Aluno com ID " + id + " não foi encontrado");
        }
        alunoRepo.deleteById(id);
    }

    //PUT
    public void atualizarPorId(Long id, AlunoDto alunoDtoAtualizado) {
        Optional<Aluno> alunoExiste = alunoRepo.findById(id);
        if (alunoExiste.isEmpty()) {
            throw new RuntimeException("Aluno com ID " + id + " não foi encontrado");
        }
        Aluno aluno = alunoExiste.get();


        Optional<Escola> escolaExiste = escolaRepo.findById(alunoDtoAtualizado.escolaId());
        if(escolaExiste.isEmpty()) {
            throw new RuntimeException("Escola com ID " + id + " não foi encontrada");
        }
        Escola escola = escolaExiste.get();

        aluno.setEscola(escola);
        aluno.setNome(alunoDtoAtualizado.nome());
        aluno.setSerie(alunoDtoAtualizado.serie());
        aluno.setTurno(alunoDtoAtualizado.turno());
        aluno.setAnoLetivo(alunoDtoAtualizado.anoLetivo());
        aluno.setObservacoes(alunoDtoAtualizado.observacoes());

        alunoRepo.save(aluno);
    }

}
