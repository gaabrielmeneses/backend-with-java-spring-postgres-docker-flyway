package br.com.menesic.GranjaApp.infrastructure.database.repository.venda;

import br.com.menesic.GranjaApp.infrastructure.database.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendaDatabaseRepository extends JpaRepository<VendaEntity, UUID> {
}
