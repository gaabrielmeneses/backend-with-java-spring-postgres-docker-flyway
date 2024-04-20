package br.com.menesic.GranjaApp.domain.adapters.pato;

import br.com.menesic.GranjaApp.domain.dto.CreatePatoDto;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.port.repository.PatoRepository;
import br.com.menesic.GranjaApp.domain.port.usecase.pato.CreatePatoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePatoUseCaseImpl implements CreatePatoUseCase {

    private final PatoRepository patoRepository;

    @Override
    public Pato save(CreatePatoDto createPatoDto) {
        log.info("[CreatePatoUseCaseImpl] - save");
        Pato mae = Pato.builderMae(createPatoDto.getPatoMae().getNome());
        Pato patoMaeSaved = patoRepository.save(mae);
        Pato filho = Pato.builderFilho(createPatoDto.getPato().getNome(), patoMaeSaved);
        Pato patoFilhoSaved = patoRepository.save(filho);

        log.info("[CreatePatoUseCaseImpl] - save: pato: {}, mae: {} SUCCESS ", patoFilhoSaved.getNome(), patoMaeSaved.getNome());
        return patoFilhoSaved;
    }
}
