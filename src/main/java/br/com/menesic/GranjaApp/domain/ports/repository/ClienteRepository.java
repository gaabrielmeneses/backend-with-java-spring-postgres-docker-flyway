package br.com.menesic.GranjaApp.domain.ports.repository;

import br.com.menesic.GranjaApp.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);

    Optional<Cliente> findByNome(String nome);
}
