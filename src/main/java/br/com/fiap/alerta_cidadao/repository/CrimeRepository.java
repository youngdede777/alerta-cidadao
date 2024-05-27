package br.com.fiap.alerta_cidadao.repository;

import br.com.fiap.alerta_cidadao.model.Crime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeRepository extends JpaRepository<Crime, Long> {
}
