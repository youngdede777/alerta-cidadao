package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.model.Localizacao;
import br.com.fiap.alerta_cidadao.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    public Localizacao salvar(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public void deletar(String cep) {
        Optional<Localizacao> localizacaoOptional = repository.findById(cep);

        if (localizacaoOptional.isPresent()) {
            repository.delete(localizacaoOptional.get());
        } else {
            throw new RuntimeException("Localização não encontrada");
        }
    }

    public List<Localizacao> listarLocalizacoes() {
        return repository.findAll();
    }
}
