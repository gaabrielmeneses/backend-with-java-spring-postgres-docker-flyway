package br.com.menesic.GranjaApp.domain.port.usecase.patomae;

import br.com.menesic.GranjaApp.domain.model.PatoMae;

public interface CreatePatoMaeUseCase {

    PatoMae save(PatoMae patoMae);
}
