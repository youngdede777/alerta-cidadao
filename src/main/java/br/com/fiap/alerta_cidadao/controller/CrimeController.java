package br.com.fiap.alerta_cidadao.controller;

import br.com.fiap.alerta_cidadao.dto.Input.InputCrime;
import br.com.fiap.alerta_cidadao.dto.Input.InputEmergencia;
import br.com.fiap.alerta_cidadao.dto.Input.InputFinalizarOcorrencia;
import br.com.fiap.alerta_cidadao.model.Crime;
import br.com.fiap.alerta_cidadao.model.Emergencia;
import br.com.fiap.alerta_cidadao.service.CrimeService;
import br.com.fiap.alerta_cidadao.service.EmergenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrimeController {

    @Autowired
    private CrimeService service;


    @GetMapping("/crime")
    public List<Crime> registrarEmergencia() {
        return service.listarCrimes();
    }

    @PostMapping("/crime/registrar")
    public ResponseEntity<String> registrarEmergencia(@RequestBody @Valid InputCrime inputCrime) {
        return service.registrarCrime(inputCrime);
    }

    @PutMapping("/crime/processar")
    public ResponseEntity<String> processarEmergencia(@RequestBody @Valid InputFinalizarOcorrencia inputFinalizarOcorrencia) {
        return service.processarCrime(inputFinalizarOcorrencia);
    }
}
