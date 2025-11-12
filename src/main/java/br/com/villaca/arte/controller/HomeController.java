package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.model.enums.TipoObra;
import br.com.villaca.arte.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "home")
public class HomeController {

    @Autowired
    ObraService obraService;

    @GetMapping("findTop5ByTipo/{tipo}")
    public ResponseEntity<List<ObraResponse>> findTop5ByTipo(@PathVariable TipoObra tipo){
        return ResponseEntity.ok(obraService.findTop5ByTipo(tipo));
    }

    @GetMapping("findByTipo/{tipo}")
    public ResponseEntity<Page<ObraResponse>> findByTipo(@PathVariable TipoObra tipo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size){
        return ResponseEntity.ok(obraService.findAllByTipo(tipo, page, size));
    }

    @GetMapping("findRandomObras")
    public ResponseEntity<List<ObraResponse>> findRandomObras(){
        return ResponseEntity.ok(obraService.findRandomObras());
    }

}
