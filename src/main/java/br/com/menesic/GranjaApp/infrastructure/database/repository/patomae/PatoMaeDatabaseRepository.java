package br.com.menesic.GranjaApp.infrastructure.database.repository.patomae;

import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoMaeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatoMaeDatabaseRepository extends JpaRepository<PatoMaeEntity, UUID> {
}
