package br.com.menesic.GranjaApp.infrastructure.database.repository.cliente;

import br.com.menesic.GranjaApp.infrastructure.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteDatabaseRepository extends JpaRepository<ClienteEntity, UUID> {
}
