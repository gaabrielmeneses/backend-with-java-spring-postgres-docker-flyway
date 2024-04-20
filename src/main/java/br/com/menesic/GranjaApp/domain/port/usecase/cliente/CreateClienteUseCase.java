package br.com.menesic.GranjaApp.domain.port.usecase.cliente;

import br.com.menesic.GranjaApp.domain.model.Cliente;

public interface CreateClienteUseCase {
    Cliente save(Cliente cliente);
}
