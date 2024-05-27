package br.com.fiap.alerta_cidadao.service;

import br.com.fiap.alerta_cidadao.model.Recurso;
import br.com.fiap.alerta_cidadao.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository repository;

    public Recurso salvar(Recurso recurso) {
        return repository.save(recurso);
    }

    public void deletar(Long id) {
        Optional<Recurso> recursoOptional = repository.findById(id);

        if (recursoOptional.isPresent()) {
            repository.delete(recursoOptional.get());
        } else {
            throw new RuntimeException("Recurso não encontrado");
        }
    }

    public List<Recurso> listarRecursos() {
        return repository.findAll();
    }

    protected List<Recurso> listarRecursosDisponiveis(String cep) {
        return repository.findRecursosDisponivelPorCep(cep);
    }

    protected void reduzirRecursoPorId(Long id) {
        repository.reduzirRecursoPorId(id);
    }

    protected void aumentarRecursoPorId(Long id) {
        repository.aumentarRecursoPorId(id);
    }

    protected void tratarAumentoDeRecursos(String cep) {
        List<Recurso> recursoList = listarRecursosDisponiveis(cep);

        if (recursoList.isEmpty()) {
            throw new RuntimeException("Não existe registro de recursos disponíveis na sua região." +
                    "Erro de server");
        } else {
            aumentarRecursoPorId(
                    recursoList.get(0).getId()
            );
        }
    }

    protected void tratarReducaoDeRecursos(String cep) {
        List<Recurso> recursoList = listarRecursosDisponiveis(cep);

        if (recursoList.isEmpty()) {
            throw new RuntimeException("Não se possuem recursos disponíveis para processar sua emergência." +
                    "Assim que estiver disponível, processaremos sua emergência");
        } else {
            reduzirRecursoPorId(
                    recursoList.get(0).getId()
            );
        }
    }
}
