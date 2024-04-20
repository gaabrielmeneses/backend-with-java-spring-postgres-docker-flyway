package br.com.menesic.GranjaApp.infrastructure.database.repository.pato;

import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PatoDatabaseRepository extends JpaRepository<PatoEntity, UUID> {

    @Query("select p from PatoEntity p where p.nome = :nome")
    Optional<PatoEntity> findByNome(@Param("nome") String nome);
}
