package br.com.menesic.GranjaApp.domain.port.repository;

import br.com.menesic.GranjaApp.domain.model.Pato;

import java.util.Optional;

public interface PatoRepository {
    Pato save(Pato pato);

    Optional<Pato> findByNome(String nome);
}
