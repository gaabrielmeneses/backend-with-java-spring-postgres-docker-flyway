package br.com.menesic.GranjaApp.domain.adapters.pato;

import br.com.menesic.GranjaApp.domain.dto.CreatePatoDto;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.ports.repository.PatoRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.pato.CreatePatoUseCase;
import jakarta.persistence.EntityExistsException;
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

        Pato patoMaeSaved = patoRepository.findByNome(createPatoDto.getPatoMae().getNome())
                .orElseGet(() -> patoRepository.save(Pato.builderMae(createPatoDto.getPatoMae().getNome())));

        Pato patoFilho = Pato.builderFilho(createPatoDto.getPato().getNome(), patoMaeSaved);

        patoRepository.findByNome(createPatoDto.getPato().getNome())
                .ifPresent(p -> {
                    throw new EntityExistsException("Este pato já está registrado em nossa base de dados!");
                });

        Pato patoFilhoSaved = patoRepository.save(patoFilho);
        log.info("[CreatePatoUseCaseImpl] - save: pato: {}, mae: {} SUCCESS ", patoFilhoSaved.getNome(), patoMaeSaved.getNome());

        return patoFilhoSaved;
    }
}
