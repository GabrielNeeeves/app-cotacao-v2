package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.infra.security.TokenService;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.dto.*;
import com.main.app_cotacao_v2.repository.usuariosRepository.ClienteRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.FuncionarioRepository;
import com.main.app_cotacao_v2.repository.usuariosRepository.UsuarioRepository;
import com.main.app_cotacao_v2.service.usuariosService.ClienteService;
import com.main.app_cotacao_v2.service.usuariosService.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    // REGISTRAR USUÁRIO
//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody UsuarioDto dto) {
//        if(this.userRepo.findByEmail(dto.email()) != null) {
//            return ResponseEntity.badRequest().build();
//        } else {
//            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
//            Usuario novoUsuario = new Usuario(dto.nome(), dto.email(), encryptedPassword, dto.role());
//            this.userRepo.save(novoUsuario);
//            return ResponseEntity.ok().build();
//        }
//    }

    // REGISTRAR CLIENTE
    @PostMapping("/register/cliente")
    public ResponseEntity registerCliente(@RequestBody ClienteDto dto) {
        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        this.clienteRepository.cadastrarClienteComUsuario(
                dto.nome(),
                dto.email(),
                encryptedPassword,
                dto.alunoId()
        );
        return ResponseEntity.ok().build();
    }

    // REGISTRAR FUNCIONARIO
    @PostMapping("/register/funcionario")
    public ResponseEntity registerFuncionario(@RequestBody FuncionarioPostDto dto) {

        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        funcionarioRepository.cadastrarFuncionario(
                dto.nome(),
                dto.email(),
                encryptedPassword,
                dto.salario(),
                dto.empresaId(),
                dto.escolaId()
        );
        return ResponseEntity.ok().build();
    }

}
