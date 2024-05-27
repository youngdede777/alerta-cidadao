package br.com.fiap.alerta_cidadao.dto.output;

import br.com.fiap.alerta_cidadao.model.Localizacao;
import br.com.fiap.alerta_cidadao.model.Suspeito;

import java.time.LocalDate;

public record OutputSuspeito(
        Long id,
        String nome,
        Character genero,
        LocalDate dataNascimento,
        Localizacao localizacao
) {
    public OutputSuspeito(Suspeito suspeito) {
        this(
                suspeito.getId(),
                suspeito.getNome(),
                suspeito.getGenero(),
                suspeito.getDataNascimento(),
                suspeito.getLocalizacao()
        );
    }
}