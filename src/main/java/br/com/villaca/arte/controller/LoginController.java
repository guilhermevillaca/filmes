package br.com.villaca.arte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

     @PostMapping("/entrar")
    public ResponseEntity<String> entrar(@RequestBody Usuario usuario) {

        Usuario usuarioIn = usuarioRepository.findByLogin(usuario.getLogin());

        if (usuarioIn.equals(usuario.getLogin()) && usuarioIn.equals(usuario.getSenha())) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    }

}
