package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.model.Suspeito;
import br.com.fiap.alerta_cidadao.service.SuspeitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuspeitoController {

    @Autowired
    private SuspeitoService service;

    @GetMapping("/suspeito/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Suspeito> listarSuspeitoPorCrime(@PathVariable(name = "id") Long id) {
        return service.listarSuspeitoPorCrime(id);
    }
}
