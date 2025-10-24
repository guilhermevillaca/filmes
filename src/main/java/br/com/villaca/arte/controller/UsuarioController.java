package br.com.villaca.arte.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.model.Usuario;
import br.com.villaca.arte.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    /*
     * C CREATE -> NOVO
     * R READ -> LISTAR E GET POR ID
     * U UPDATE -> ATUALIZAR
     * D DELETE -> REMOVER
     */

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok((List<Usuario>) usuarioRepository.findAll());
    }

    // localhost:8080/usuario/listar/10
    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Integer id) {
        Optional<Usuario> object = usuarioRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(object.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<Usuario> novo(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> remover(@PathVariable(value = "id") Integer id) {
        Optional<Usuario> object = usuarioRepository.findById(id);
        if (object.isPresent()) {
            usuarioRepository.delete(object.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Integer id,
            Usuario novoUsuario) {
        Optional<Usuario> object = usuarioRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(usuarioRepository.save(novoUsuario), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
