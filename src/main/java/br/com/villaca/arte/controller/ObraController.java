package br.com.villaca.arte.controller;

import br.com.villaca.arte.dto.request.ObraRequest;
import br.com.villaca.arte.dto.response.ObraResponse;
import br.com.villaca.arte.service.ObraService;
import br.com.villaca.arte.util.api.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "obra")
public class ObraController extends Controller<UUID, ObraService, ObraResponse, ObraRequest> {

}
