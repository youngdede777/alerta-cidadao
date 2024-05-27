package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.model.Localizacao;
import br.com.fiap.alerta_cidadao.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @GetMapping("/localizacao")
    @ResponseStatus(HttpStatus.OK)
    public List<Localizacao> listarTodosContatos() {
        return service.listarLocalizacoes();
    }

    @PostMapping("/localizacao")
    @ResponseStatus(HttpStatus.CREATED)
    public Localizacao gravar(@RequestBody @Valid Localizacao localizacao) {
        return service.salvar(localizacao);
    }

    @DeleteMapping("/localizacao/{cep}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable(name = "cep") String cep) {
        service.deletar(cep);
    }

}
