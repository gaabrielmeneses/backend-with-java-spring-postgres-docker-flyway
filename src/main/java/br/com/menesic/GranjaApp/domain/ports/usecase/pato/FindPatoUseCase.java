package br.com.menesic.GranjaApp.domain.ports.usecase.pato;

import br.com.menesic.GranjaApp.domain.model.Pato;

import java.util.Optional;

public interface FindPatoUseCase {
    Optional<Pato> findByNome(String nome);
}
