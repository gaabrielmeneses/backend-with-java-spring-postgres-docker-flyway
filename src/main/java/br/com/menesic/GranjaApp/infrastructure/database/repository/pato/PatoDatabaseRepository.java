package br.com.menesic.GranjaApp.infrastructure.database.repository.pato;

import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatoDatabaseRepository extends JpaRepository<PatoEntity, UUID> {

}
