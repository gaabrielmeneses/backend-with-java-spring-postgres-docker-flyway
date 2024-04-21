package br.com.menesic.GranjaApp.domain.ports.repository;

import br.com.menesic.GranjaApp.domain.model.Venda;

import java.util.List;

public interface VendaRepository {
    Venda save(Venda venda);

    List<Venda> findAll();
}
