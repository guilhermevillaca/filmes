package br.com.villaca.arte.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
*
* My Generic Controller
* */
public class Controller<
        PK,
        SERVICE extends GenericService<PK, RESPONSE, REQUEST>,
        RESPONSE,
        REQUEST> {

    @Autowired
    SERVICE service;

    @GetMapping
    public ResponseEntity<List<RESPONSE>> findAll() {
        return ResponseEntity.ok((List<RESPONSE>) service.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<RESPONSE>> findAllPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(service.findAllPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESPONSE> findById(@PathVariable PK id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RESPONSE> create(@RequestBody REQUEST obra) {
        return new ResponseEntity<RESPONSE>(service.create(obra), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable PK id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // retorna 204 sucesso sem corpo
    }

    @PutMapping("/{id}")
    public ResponseEntity<RESPONSE> update(@PathVariable PK id, @RequestBody REQUEST novo) {
        RESPONSE atualizado = service.update(id, novo);
        return ResponseEntity.ok(atualizado);
    }

}
