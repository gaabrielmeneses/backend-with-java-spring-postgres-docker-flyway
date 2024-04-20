package br.com.menesic.GranjaApp.infrastructure.database.repository.patomae;

import br.com.menesic.GranjaApp.domain.model.PatoMae;
import br.com.menesic.GranjaApp.domain.port.repository.PatoMaeRepository;
import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoMaeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PatoMaeRepositoryImpl implements PatoMaeRepository {

    private final PatoMaeDatabaseRepository databaseRepository;
    private final ModelMapper modelMapper;

    @Override
    public PatoMae save(PatoMae patoMae) {
        log.info("[PatoMaeRepositoryImpl] - save - pato: {}, mae: {}", patoMae.getPato().getNome(), patoMae.getMae().getNome());
        PatoMaeEntity patoSaved = databaseRepository.save(modelMapper.map(patoMae, PatoMaeEntity.class));
        return modelMapper.map(patoSaved, PatoMae.class);
    }
}
