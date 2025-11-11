package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.AvaliacaoRequest;
import br.com.villaca.arte.dto.response.AvaliacaoResponse;
import br.com.villaca.arte.service.AvaliacaoService;
import br.com.villaca.arte.util.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "avaliacao")
public class AvaliacaoController extends Controller<UUID, AvaliacaoService, AvaliacaoResponse, AvaliacaoRequest> {

    @Autowired
    AvaliacaoService service;

    @GetMapping("findByObra_Id/{id_obra}")
    public ResponseEntity<Page<AvaliacaoResponse>> findByObra_Id(@PathVariable UUID id_obra, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(service.listarPorObra(id_obra, page, size));
    }


}
