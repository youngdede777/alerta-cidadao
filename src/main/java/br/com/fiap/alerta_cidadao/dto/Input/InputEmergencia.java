package br.com.fiap.alerta_cidadao.dto.Input;

import br.com.fiap.alerta_cidadao.model.enumerator.TipoEmergenciaEnum;
import jakarta.validation.constraints.NotNull;

public record InputEmergencia(
        Long id,
        @NotNull(message = "Tipo da emergencia é obrigatória")
        TipoEmergenciaEnum tipoEmergencia,
        @NotNull(message = "Cep da emergência é obrigatório")
        String cep

) {
}