package br.com.menesic.GranjaApp.domain.port.repository;

import br.com.menesic.GranjaApp.domain.model.Cliente;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
}
