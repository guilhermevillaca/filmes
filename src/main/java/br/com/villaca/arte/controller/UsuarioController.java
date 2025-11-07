package br.com.villaca.arte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.service.UsuarioService;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok((List<UsuarioResponse>) service.listarTodos());
    }

    // localhost:8080/Obra/listar/10
    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<UsuarioResponse> novo(@RequestBody UsuarioRequest obra) {
        return new ResponseEntity<UsuarioResponse>(service.salvar(obra), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // retorna 204 sucesso sem corpo
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id,
            @RequestBody UsuarioRequest novoObra) {
        UsuarioResponse atualizado = service.atualizar(id, novoObra);
        return ResponseEntity.ok(atualizado);
    }

}
