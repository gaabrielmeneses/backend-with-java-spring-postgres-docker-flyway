package br.com.menesic.GranjaApp.infrastructure.database.repository.patomae;

import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoMaeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PatoMaeDatabaseRepository extends PagingAndSortingRepository<PatoMaeEntity, UUID> {
}
