package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsuarioRequest usuario) {
        Usuario usuarioIn = service.getByLogin(usuario.login());        //Usuario e senha da interface
        String login = usuario.login();
        String senha = usuario.senha();
        //Usuario e senha do banco
        String loginBanco = usuarioIn.getLogin();
        String senhaBanco = usuarioIn.getSenha();
        if (loginBanco.equals(login) && senhaBanco.equals(senha)) {
            return new ResponseEntity<>(usuarioIn, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    }
}
