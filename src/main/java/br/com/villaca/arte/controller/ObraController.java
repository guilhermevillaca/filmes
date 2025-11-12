package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.model.Obra;
import br.com.villaca.arte.model.enums.TipoObra;
import br.com.villaca.arte.service.ObraService;
import br.com.villaca.arte.util.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "obra")
public class ObraController extends Controller<UUID, ObraService, ObraResponse, ObraRequest> {

    @Autowired
    ObraService obraService;

    @GetMapping("findByTipo/{tipo}")
    public ResponseEntity<Page<ObraResponse>> findByTipo(@PathVariable TipoObra tipo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size){
        return ResponseEntity.ok(obraService.findAllByTipo(tipo, page, size));
    }

    @GetMapping("findTop5ByTipo/{tipo}")
    public ResponseEntity<List<ObraResponse>> findTop5ByTipo(@PathVariable TipoObra tipo){
        return ResponseEntity.ok(obraService.findTop5ByTipo(tipo));
    }

    @GetMapping("findByGenero/{genero_id}")
    public ResponseEntity<Page<ObraResponse>> findByGenero(@PathVariable UUID genero_id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size){
        return ResponseEntity.ok(obraService.findByGenero(genero_id, page, size));
    }

}
