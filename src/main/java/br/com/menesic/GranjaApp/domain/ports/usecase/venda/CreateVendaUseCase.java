package br.com.menesic.GranjaApp.domain.ports.usecase.venda;

import br.com.menesic.GranjaApp.domain.dto.VendaDto;

public interface CreateVendaUseCase {
    void save(VendaDto vendaDto);
}
