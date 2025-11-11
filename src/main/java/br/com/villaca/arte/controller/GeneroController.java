package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.GeneroRequest;
import br.com.villaca.arte.dto.response.GeneroResponse;
import br.com.villaca.arte.service.GeneroService;
import br.com.villaca.arte.util.api.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "genero")
public class GeneroController extends Controller<UUID, GeneroService, GeneroResponse, GeneroRequest> {

}
