package br.com.menesic.GranjaApp.domain.port.usecase;

import br.com.menesic.GranjaApp.domain.model.Pato;
import org.springframework.transaction.annotation.Transactional;

public interface CreatePatoUseCase {
    @Transactional
    Pato save(Pato pato);
}
