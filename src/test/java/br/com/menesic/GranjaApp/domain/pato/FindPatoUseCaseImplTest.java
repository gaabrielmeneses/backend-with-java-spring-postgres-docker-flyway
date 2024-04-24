package br.com.menesic.GranjaApp.domain.pato;

import br.com.menesic.GranjaApp.domain.adapters.pato.FindPatoUseCaseImpl;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.ports.repository.PatoRepository;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

@Slf4j
public class FindPatoUseCaseImplTest {

    @Mock
    private PatoRepository patoRepository;

    @InjectMocks
    private FindPatoUseCaseImpl findPatoUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        findPatoUseCase = new FindPatoUseCaseImpl(patoRepository);
    }

    @Test
    public void testFindByNome() {
        String nome = "Menesic1.0";
        Pato pato = easyRandom.nextObject(Pato.class);

        when(patoRepository.findByNome(nome)).thenReturn(Optional.of(pato));
        Optional<Pato> foundPato = findPatoUseCase.findByNome(nome);

        verify(patoRepository, times(1)).findByNome(nome);
        assertEquals(pato, foundPato.orElse(null));
    }

    @Test
    public void testFindByNomeNotFound() {
        String nome = "Filhote2";

        when(patoRepository.findByNome(nome)).thenReturn(Optional.empty());
        Optional<Pato> foundPato = findPatoUseCase.findByNome(nome);

        verify(patoRepository, times(1)).findByNome(nome);
        assertEquals(Optional.empty(), foundPato);
    }
}
