package br.com.menesic.GranjaApp.domain.adapters.patomae;

import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.model.PatoMae;
import br.com.menesic.GranjaApp.domain.port.usecase.pato.CreatePatoUseCase;
import br.com.menesic.GranjaApp.domain.port.usecase.pato.FindPatoUseCase;
import br.com.menesic.GranjaApp.domain.port.usecase.patomae.CreatePatoMaeUseCase;
import br.com.menesic.GranjaApp.infrastructure.database.repository.patomae.PatoMaeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePatoMaeUseCaseImpl implements CreatePatoMaeUseCase {

    private final PatoMaeRepositoryImpl patoMaeRepository;
    private final CreatePatoUseCase createPatoUseCase;
    private final FindPatoUseCase findPatoUseCase;

    @Override
    @Transactional
    public PatoMae save(PatoMae patoMae) {
        log.info("[CreatePatoMaeUseCaseImpl] - save");
        patoMae = mapMae(patoMae);
        patoMae = mapFilho(patoMae);
        PatoMae patoMaeSaved = patoMaeRepository.save(patoMae);
        log.info("[CreatePatoMaeUseCaseImpl] - save - pato: {}, mae: {} - SUCCESS", patoMae.getPato().getNome(), patoMae.getMae().getNome());
        return patoMaeSaved;
    }

    private PatoMae mapFilho(PatoMae patoMae) {
        Pato filho = createPatoUseCase.save(
            Pato.builderFilho(
                patoMae.getPato().getNome(),
                patoMae.getPato().getVendido()
            )
        );
        patoMae.setPato(filho);
        return patoMae;
    }

    private PatoMae mapMae(PatoMae patoMae) {
        Pato mae = patoMae.getMae();
        findPatoUseCase.findByNome(mae.getNome()).ifPresentOrElse(
            pato -> patoMae.setMae(pato),
            () -> patoMae.setMae(
                createPatoUseCase.save(
                    Pato.builderMae(
                        mae.getNome(),
                        mae.getVendido()
                    )
                )
            )
        );
        return patoMae;
    }

}
