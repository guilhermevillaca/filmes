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

import br.com.villaca.arte.dto.request.GeneroRequest;
import br.com.villaca.arte.dto.response.GeneroResponse;
import br.com.villaca.arte.service.GeneroService;

@RestController
@RequestMapping(value = "genero")
public class GeneroController {
    @Autowired
    GeneroService service;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<GeneroResponse>> listar() {
        return ResponseEntity.ok((List<GeneroResponse>) service.listarTodos());
    }

    // localhost:8080/Obra/listar/10
    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<GeneroResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<GeneroResponse> novo(@RequestBody GeneroRequest obra) {
        return new ResponseEntity<GeneroResponse>(service.salvar(obra), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // retorna 204 sucesso sem corpo
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GeneroResponse> atualizar(@PathVariable Integer id,
            @RequestBody GeneroRequest novoObra) {
        GeneroResponse atualizado = service.atualizar(id, novoObra);
        return ResponseEntity.ok(atualizado);
    }
}
