package br.com.menesic.GranjaApp.infrastructure.database.repository.cliente;

import br.com.menesic.GranjaApp.infrastructure.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClienteDatabaseRepository extends JpaRepository<ClienteEntity, UUID> {

    @Query("select c from ClienteEntity c where c.nome = :nome")
    Optional<ClienteEntity> findByNome(@Param("nome") String nome);
}
