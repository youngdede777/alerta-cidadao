package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.dto.Input.InputSuspeito;
import br.com.fiap.alerta_cidadao.model.Suspeito;
import br.com.fiap.alerta_cidadao.repository.SuspeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuspeitoService {

    @Autowired
    private SuspeitoRepository suspeitoRepository;

    protected void registrarSuspeitos(List<InputSuspeito> inputSuspeitos, Long crimeId) {
        List<Suspeito> suspeitos = new ArrayList<>();
        for(InputSuspeito s : inputSuspeitos) {
            Suspeito suspeito = new Suspeito();
            suspeito.setNome(s.nome());
            suspeito.setGenero(s.genero());
            suspeito.setDataNascimento(s.dataNascimento());
            suspeito.setCrimeId(crimeId);

            suspeitos.add(suspeito);
        }

        suspeitoRepository.saveAll(suspeitos);
    }

    public List<Suspeito> listarSuspeitoPorCrime(Long crimeId) {
        return suspeitoRepository.listarSuspeitoPorCrime(crimeId);
    }
}
