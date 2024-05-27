package br.com.fiap.alerta_cidadao.repository;

import br.com.fiap.alerta_cidadao.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {
}
