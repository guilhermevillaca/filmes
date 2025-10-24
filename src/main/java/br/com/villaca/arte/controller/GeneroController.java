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

import br.com.villaca.arte.model.Avaliacao;
import br.com.villaca.arte.model.Genero;
import br.com.villaca.arte.repository.AvaliacaoRepository;
import br.com.villaca.arte.repository.GeneroRepository;

@RestController
@RequestMapping(value = "genero")
public class GeneroController {
    @Autowired
    GeneroRepository generoRepository;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<Genero>> listar() {
        return ResponseEntity.ok((List<Genero>) generoRepository.findAll());
    }

    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Genero> getById(@PathVariable(value = "id") Integer id) {
        Optional<Genero> object = generoRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(object.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<Genero> novo(@RequestBody Genero genero) {
        return new ResponseEntity<Genero>(generoRepository.save(genero), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Genero> remover(@PathVariable(value = "id") Integer id) {
        Optional<Genero> object = generoRepository.findById(id);
        if (object.isPresent()) {
            generoRepository.delete(object.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Genero> atualizar(@PathVariable(value = "id") Integer id,
            Genero novoGenero) {
        Optional<Genero> object = generoRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(generoRepository.save(novoGenero), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
