package br.com.menesic.GranjaApp.domain.port.usecase.pato;

import br.com.menesic.GranjaApp.domain.dto.CreatePatoDto;
import br.com.menesic.GranjaApp.domain.model.Pato;

public interface CreatePatoUseCase {
    Pato save(CreatePatoDto createPatoDto);
}
