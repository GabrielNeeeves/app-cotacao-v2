package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.infra.security.TokenService;
import com.main.app_cotacao_v2.model.usuariosModel.Usuario;
import com.main.app_cotacao_v2.model.usuariosModel.dto.AuthenticationDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.LoginResponseDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.RegisterDto;
import com.main.app_cotacao_v2.model.usuariosModel.dto.UsuarioDto;
import com.main.app_cotacao_v2.repository.usuariosRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDto dto) {
        if(this.userRepo.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
            Usuario novoUsuario = new Usuario(dto.nome(), dto.email(), encryptedPassword, dto.role());
            this.userRepo.save(novoUsuario);
            return ResponseEntity.ok().build();
        }
    }

}
