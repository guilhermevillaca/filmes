package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.AvaliacaoRequest;
import br.com.villaca.arte.dto.response.AvaliacaoResponse;
import br.com.villaca.arte.service.AvaliacaoService;
import br.com.villaca.arte.util.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "avaliacao")
public class AvaliacaoController extends Controller<UUID, AvaliacaoService, AvaliacaoResponse, AvaliacaoRequest> {

}
