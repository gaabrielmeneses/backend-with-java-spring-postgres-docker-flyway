package br.com.menesic.GranjaApp.domain.ports.usecase.cliente;

import br.com.menesic.GranjaApp.domain.model.Cliente;

import java.util.Optional;

public interface FindClienteUseCase {
    Optional<Cliente> findByNome(String nome);
}
