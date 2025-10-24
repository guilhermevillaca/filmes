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
import br.com.villaca.arte.repository.AvaliacaoRepository;

@RestController
@RequestMapping(value = "avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoRepository avalicaoRepository;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<List<Avaliacao>> listar() {
        return ResponseEntity.ok((List<Avaliacao>) avalicaoRepository.findAll());
    }

    @RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Avaliacao> getById(@PathVariable(value = "id") Integer id) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(object.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ResponseEntity<Avaliacao> novo(@RequestBody Avaliacao avaliacao) {
        return new ResponseEntity<Avaliacao>(avalicaoRepository.save(avaliacao), HttpStatus.OK);
    }

    @RequestMapping(value = "remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Avaliacao> remover(@PathVariable(value = "id") Integer id) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        if (object.isPresent()) {
            avalicaoRepository.delete(object.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Avaliacao> atualizar(@PathVariable(value = "id") Integer id,
            Avaliacao novaAvaliacao) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(avalicaoRepository.save(novaAvaliacao), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
