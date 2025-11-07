package br.com.villaca.arte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.service.ObraService;

@RestController
@RequestMapping(value = "obra")
public class ObraController {

    @Autowired
    ObraService service;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<ObraResponse>> listar() {
        return ResponseEntity.ok((List<ObraResponse>) service.listarTodos());
    }

    @GetMapping("listar/paginado")
    public ResponseEntity<Page<ObraResponse>> listarPaginado(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(service.listarPaginado(page, size));
    }

    // localhost:8080/Obra/listar/10
    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObraResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<ObraResponse> novo(@RequestBody ObraRequest obra) {
        return new ResponseEntity<ObraResponse>(service.salvar(obra), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // retorna 204 sucesso sem corpo
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ObraResponse> atualizar(@PathVariable Integer id,
            @RequestBody ObraRequest novoObra) {
        ObraResponse atualizado = service.atualizar(id, novoObra);
        return ResponseEntity.ok(atualizado);
    }

}
