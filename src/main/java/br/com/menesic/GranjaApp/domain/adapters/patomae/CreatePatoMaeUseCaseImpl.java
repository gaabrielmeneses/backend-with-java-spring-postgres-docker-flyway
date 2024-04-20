package br.com.menesic.GranjaApp.domain.adapters.patomae;

import br.com.menesic.GranjaApp.domain.enums.ValorPatoEnum;
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
        patoMae = saveMae(patoMae);
        patoMae = saveFilho(patoMae);
        PatoMae patoMaeSaved = patoMaeRepository.save(patoMae);
        log.info("[CreatePatoMaeUseCaseImpl] - save - pato: {}, mae: {} - SUCCESS", patoMae.getFilho().getNome(), patoMae.getMae().getNome());
        return patoMaeSaved;
    }

    private PatoMae saveFilho(PatoMae patoMae) {
        Pato filho = patoMae.getFilho();
        createPatoUseCase.save(
            Pato.builderFilho(
                filho.getNome(),
                filho.getVendido()
            )
        );
        patoMae.setFilho(filho);
        return patoMae;
    }

    private PatoMae saveMae(PatoMae patoMae) {
        Pato mae = patoMae.getMae();
        findPatoUseCase.findByNome(mae.getNome())
            .ifPresentOrElse(
                pato -> {
                    if(!pato.getMae()){
                        pato.setMae(true);
                        pato.setValor(ValorPatoEnum.VALOR_PATO_MAE.getValor());
                    }
                    patoMae.setMae(pato);
                },
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
