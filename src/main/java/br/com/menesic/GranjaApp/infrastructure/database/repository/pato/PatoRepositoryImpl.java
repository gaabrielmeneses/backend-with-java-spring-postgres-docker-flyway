package br.com.menesic.GranjaApp.infrastructure.database.repository.pato;

import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.ports.repository.PatoRepository;
import br.com.menesic.GranjaApp.infrastructure.database.entity.PatoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PatoRepositoryImpl implements PatoRepository {

    private final PatoDatabaseRepository databaseRepository;
    private final ModelMapper modelMapper;

    @Override
    public Pato save(Pato pato) {
        log.info("[PatoRepositoryImpl] - save: {}", pato.getNome());
        PatoEntity patoSaved = databaseRepository.save(modelMapper.map(pato, PatoEntity.class));
        return modelMapper.map(patoSaved, Pato.class);
    }

    @Override
    public Optional<Pato> findByNome(String nome) {
        log.info("[PatoRepositoryImpl] - findByNome: {}", nome);
        Optional<PatoEntity> patoSaved = databaseRepository.findByNome(nome);
        return Optional.ofNullable(modelMapper.map(patoSaved, Pato.class));
    }
}
