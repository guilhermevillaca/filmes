package br.com.villaca.arte.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.model.Login;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;


@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/entrar")
    public ResponseEntity<Object> entrar(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioIn = usuarioRepository.findByLogin(usuario.getLogin());        
        if (usuarioIn.isPresent() && usuarioIn.get().getLogin().equals(usuario.getLogin()) && usuarioIn.get().getSenha().equals(usuario.getSenha())) {
            return new ResponseEntity<>(usuarioIn.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    }

}
