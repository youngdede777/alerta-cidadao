package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.dto.Input.InputEmergencia;
import br.com.fiap.alerta_cidadao.dto.Input.InputFinalizarOcorrencia;
import br.com.fiap.alerta_cidadao.model.Emergencia;
import br.com.fiap.alerta_cidadao.repository.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmergenciaService {

    @Autowired
    private EmergenciaRepository repository;

    @Autowired
    private RecursoService recursoService;

    public ResponseEntity<String> registrarEmergencia(InputEmergencia inputEmergencia) {
        recursoService.tratarReducaoDeRecursos(inputEmergencia.cep());

        Emergencia emergencia = new Emergencia();
        emergencia.setTipoEmergencia(inputEmergencia.tipoEmergencia());
        //Equivale a Aberto
        emergencia.setStatus('A');
        emergencia.setDataInicio(LocalDate.now());
        emergencia.setCepLocalizacao(inputEmergencia.cep());
        repository.save(emergencia);

        return ResponseEntity.ok("Emergência registrada com Sucesso.");
    }

    @Transactional
    public ResponseEntity<String> processarEmergencia(InputFinalizarOcorrencia input) {
        Optional<Emergencia> optionalEmergencia = repository.findById(input.id());

        validarProcessoDeEmergencia(input, optionalEmergencia);

        Emergencia emergencia = optionalEmergencia.get();

        if (emergencia.getStatus() != 'A') {
            throw new RuntimeException("Essa emergência já foi finalizada ou encerrada.");
        }

        recursoService.tratarAumentoDeRecursos(emergencia.getCepLocalizacao());

        emergencia.setStatus(input.tipoEncerramento());
        emergencia.setDataFim(LocalDate.now());
        repository.save(emergencia);

        return ResponseEntity.ok("Emergência finalizada com Sucesso.");
    }

    @Transactional
    private static void validarProcessoDeEmergencia(InputFinalizarOcorrencia input, Optional<Emergencia> optionalEmergencia) {
        if (optionalEmergencia.isEmpty()) {
            throw new RuntimeException("Emergência inexistente para Id informado.");
        }

        //Se equivale a encerrado ou cancelado
        if (input.tipoEncerramento() != 'E' && input.tipoEncerramento() != 'C') {
            throw new RuntimeException("Emergência já está encerrada ou finalizada.");
        }
    }

    public List<Emergencia> listarEmergencias() {
        return repository.findAll();
    }
}
