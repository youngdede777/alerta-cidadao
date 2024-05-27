package br.com.fiap.alerta_cidadao.repository;

import br.com.fiap.alerta_cidadao.model.Emergencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergenciaRepository extends JpaRepository<Emergencia, Long> {
}
