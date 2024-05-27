package br.com.fiap.alerta_cidadao.repository;

import br.com.fiap.alerta_cidadao.model.Suspeito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuspeitoRepository extends JpaRepository<Suspeito, Long> {

    @Query("SELECT s from Suspeito s " +
            "where s.crimeId = :crimeId")
    List<Suspeito> listarSuspeitoPorCrime(@Param("crimeId") Long crimeId);
}