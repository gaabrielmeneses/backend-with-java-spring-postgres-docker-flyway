package br.com.menesic.GranjaApp.domain.ports.repository;

import br.com.menesic.GranjaApp.domain.model.Venda;

public interface VendaRepository {
    Venda save(Venda venda);
}
