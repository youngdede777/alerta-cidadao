package br.com.fiap.alerta_cidadao.repository;

import br.com.fiap.alerta_cidadao.model.Recurso;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    @Query("SELECT rc from Recurso rc " +
            "WHERE rc.cepLocalizacao = :cep " +
            "AND rc.disponivel > 0")
    List<Recurso> findRecursosDisponivelPorCep(@Param("cep") String cep);


    @Modifying
    @Transactional
    @Query("UPDATE Recurso rc SET rc.disponivel = rc.disponivel - 1 " +
            "WHERE rc.id = :id AND rc.disponivel > 0")
    void reduzirRecursoPorId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Recurso rc SET rc.disponivel = rc.disponivel + 1 " +
            "WHERE rc.id = :id AND rc.disponivel > 0")
    void aumentarRecursoPorId(@Param("id") Long id);
}
