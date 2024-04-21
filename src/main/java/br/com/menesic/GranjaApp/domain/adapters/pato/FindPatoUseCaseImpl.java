package br.com.menesic.GranjaApp.domain.adapters.pato;

import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.ports.repository.PatoRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.pato.FindPatoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindPatoUseCaseImpl implements FindPatoUseCase {

    private final PatoRepository patoRepository;

    @Override
    public Optional<Pato> findByNome(String nome) {
        return patoRepository.findByNome(nome);
    }
}
