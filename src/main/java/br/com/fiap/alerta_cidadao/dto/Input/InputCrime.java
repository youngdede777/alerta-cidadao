package br.com.fiap.alerta_cidadao.dto.Input;

import br.com.fiap.alerta_cidadao.model.enumerator.TipoCrimeEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record InputCrime(
        Long id,
        @NotNull(message = "Tipo do crime é obrigatório")
        TipoCrimeEnum tipoCrime,
        @NotNull(message = "Data do crime  é obrigatória")
        LocalDate data,
        @NotNull(message = "Cep do crime é obrigatório")
        String cep,
        List<InputSuspeito> suspeitos
) {
}