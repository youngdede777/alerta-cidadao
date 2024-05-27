package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.dto.Input.InputCrime;
import br.com.fiap.alerta_cidadao.dto.Input.InputFinalizarOcorrencia;
import br.com.fiap.alerta_cidadao.model.Crime;
import br.com.fiap.alerta_cidadao.repository.CrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CrimeService {

    @Autowired
    private CrimeRepository repository;

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private SuspeitoService suspeitoService;

    @Transactional
    public ResponseEntity<String> registrarCrime(InputCrime inputCrime) {
        recursoService.tratarReducaoDeRecursos(inputCrime.cep());

        Crime crime = new Crime();
        crime.setTipoCrime(inputCrime.tipoCrime());
        //Equivale a Aberto
        crime.setStatus('A');
        crime.setData(inputCrime.data());
        crime.setCepLocalizacao(inputCrime.cep());
        Crime resultadoCrime = repository.save(crime);

        if (!inputCrime.suspeitos().isEmpty()) {
            suspeitoService.registrarSuspeitos(inputCrime.suspeitos(), resultadoCrime.getId());
        }

        return ResponseEntity.ok("Crime registrada com Sucesso.");
    }

    @Transactional
    public ResponseEntity<String> processarCrime(InputFinalizarOcorrencia input) {
        Optional<Crime> optionalCrime = repository.findById(input.id());

        validarProcessoDeCrime(input, optionalCrime);

        Crime crime = optionalCrime.get();

        if (crime.getStatus() != 'A') {
            throw new RuntimeException("Esse Crime já foi finalizada ou encerrada.");
        }

        recursoService.tratarAumentoDeRecursos(crime.getCepLocalizacao());

        crime.setStatus(input.tipoEncerramento());
        repository.save(crime);

        return ResponseEntity.ok("Crime finalizado com Sucesso.");
    }

    private static void validarProcessoDeCrime(InputFinalizarOcorrencia input, Optional<Crime> optionalCrime) {
        if (optionalCrime.isEmpty()) {
            throw new RuntimeException("Crime inexistente para Id informado.");
        }

        //Se equivale a encerrado ou cancelado
        if (input.tipoEncerramento() != 'E' && input.tipoEncerramento() != 'C') {
            throw new RuntimeException("Crime já está encerrado ou finalizado.");
        }
    }

    public List<Crime> listarCrimes() {
        return repository.findAll();
    }
}
