package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.LoginRequest;
import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.security.JwtTokenProvider;
import br.com.villaca.arte.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws Exception {
        Usuario usuario = service.getByLogin(request.login());

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtTokenProvider.generateToken(usuario.getLogin());
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@RequestBody UsuarioRequest request) {
        UsuarioRequest request1 = new UsuarioRequest(
                request.nome(),
                request.email(),
                request.login(),
                request.senha(),
                request.dataCadastro()
        );
        return new ResponseEntity<UsuarioResponse>(service.salvar(request), HttpStatus.OK);
    }
}
