package br.com.villaca.arte.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.model.Login;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/entrar")
    public ResponseEntity<Object> entrar(@RequestBody Login usuario) {
        System.out.println(usuario.getLogin());
        System.out.println(usuario.getSenha());

        Optional<Usuario> usuarioIn = usuarioRepository.findByLogin("guilherme.villaca");
        System.out.println(usuarioIn.get().getLogin());
        if (usuarioIn.isPresent() && usuarioIn.get().equals(usuario.getLogin()) && usuarioIn.get().equals(usuario.getSenha())) {
            return new ResponseEntity<>(usuarioIn.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    }

}
