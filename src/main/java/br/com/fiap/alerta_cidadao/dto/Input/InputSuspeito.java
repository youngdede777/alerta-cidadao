package br.com.fiap.alerta_cidadao.dto.Input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InputSuspeito(
        Long id,
        @NotBlank(message = "Nome do suspeito é obrigatório")
        String nome,
        Character genero,
        Long crimeId,
        @NotNull(message = "Data de nascimento do contato é obrigatória")
        LocalDate dataNascimento
) {
}
