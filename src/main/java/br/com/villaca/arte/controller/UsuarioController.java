package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.UsuarioRequest;
import br.com.villaca.arte.dto.response.UsuarioResponse;
import br.com.villaca.arte.service.UsuarioService;
import br.com.villaca.arte.util.api.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController extends Controller<UUID, UsuarioService, UsuarioResponse, UsuarioRequest> {



}
