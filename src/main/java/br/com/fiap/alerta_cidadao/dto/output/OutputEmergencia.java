package br.com.fiap.alerta_cidadao.dto.output;

import br.com.fiap.alerta_cidadao.model.Emergencia;
import br.com.fiap.alerta_cidadao.model.Localizacao;
import br.com.fiap.alerta_cidadao.model.enumerator.TipoEmergenciaEnum;

import java.time.LocalDate;

public record OutputEmergencia(

        Long id,
        TipoEmergenciaEnum tipoEmergencia,
        Character status,
        LocalDate dataInicio,
        LocalDate dataFim,
        Localizacao localizacao
) {
    public OutputEmergencia(Emergencia emergencia) {
        this(
                emergencia.getId(),
                emergencia.getTipoEmergencia(),
                emergencia.getStatus(),
                emergencia.getDataInicio(),
                emergencia.getDataFim(),
                emergencia.getLocalizacao()
        );
    }

}
