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

    @GetMapping("findByObra_Id/{obra_id}")
    public ResponseEntity<Page<AvaliacaoResponse>> findByObra_Id(@PathVariable UUID obra_id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
        return ResponseEntity.ok(service.findByObra(obra_id, page, size));
    }


}
