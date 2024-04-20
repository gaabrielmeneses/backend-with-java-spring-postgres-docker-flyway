package br.com.menesic.GranjaApp.domain.adapters.pato;

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
    public Pato save(Pato pato) {
        log.info("[CreatePatoUseCaseImpl] - save");
        Pato patoSaved = patoRepository.save(pato);
        log.info("[CreatePatoUseCaseImpl] - save: {} SUCCESS ", patoSaved.getNome());
        return patoSaved;
    }
}
