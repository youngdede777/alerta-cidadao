package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.dto.Input.InputEmergencia;
import br.com.fiap.alerta_cidadao.dto.Input.InputFinalizarOcorrencia;
import br.com.fiap.alerta_cidadao.model.Emergencia;
import br.com.fiap.alerta_cidadao.service.EmergenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmergenciaController {

    @Autowired
    private EmergenciaService service;


    @GetMapping("/emergencia")
    public List<Emergencia> registrarEmergencia() {
        return service.listarEmergencias();
    }

    @PostMapping("/emergencia/registrar")
    public ResponseEntity<String> registrarEmergencia(@RequestBody @Valid InputEmergencia inputEmergencia) {
        return service.registrarEmergencia(inputEmergencia);
    }

    @PutMapping("/emergencia/processar")
    public ResponseEntity<String> processarEmergencia(@RequestBody @Valid InputFinalizarOcorrencia inputFinalizarOcorrencia) {
        return service.processarEmergencia(inputFinalizarOcorrencia);
    }
}
