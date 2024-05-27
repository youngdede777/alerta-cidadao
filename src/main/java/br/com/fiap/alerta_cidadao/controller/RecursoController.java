package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.model.Recurso;
import br.com.fiap.alerta_cidadao.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecursoController {

    @Autowired
    private RecursoService service;

    @GetMapping("/recurso")
    @ResponseStatus(HttpStatus.OK)
    public List<Recurso> listarTodosContatos() {
        return service.listarRecursos();
    }

    @PostMapping("/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    public Recurso gravar(@RequestBody @Valid Recurso recurso) {
        return service.salvar(recurso);
    }

    @DeleteMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable(name = "id") Long id) {
        service.deletar(id);
    }
}
