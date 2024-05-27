package br.com.fiap.alerta_cidadao.dto.output;

import br.com.fiap.alerta_cidadao.model.Crime;
import br.com.fiap.alerta_cidadao.model.Localizacao;
import br.com.fiap.alerta_cidadao.model.enumerator.TipoCrimeEnum;

import java.time.LocalDate;
import java.util.List;

public record OutputCrime (
        Long id,
        TipoCrimeEnum tipoCrime,
        Character status,
        LocalDate data,
        Localizacao localizacao,
        List<OutputSuspeito> suspeitos
){

    public OutputCrime(Crime crime, List<OutputSuspeito> suspeitos) {
        this(
                crime.getId(),
                crime.getTipoCrime(),
                crime.getStatus(),
                crime.getData(),
                crime.getLocalizacao(),
                suspeitos
        );
    }
}
