package br.com.menesic.GranjaApp.domain.pato;

import br.com.menesic.GranjaApp.domain.adapters.pato.CreatePatoUseCaseImpl;
import br.com.menesic.GranjaApp.domain.dto.CreatePatoDto;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.ports.repository.PatoRepository;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

@Slf4j
public class CreatePatoUseCaseImplTest {

    @Mock
    private PatoRepository patoRepository;

    @InjectMocks
    private CreatePatoUseCaseImpl createPatoUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createPatoUseCase = new CreatePatoUseCaseImpl(patoRepository);
    }

    @Test
    public void testSave() {
        CreatePatoDto createPatoDto = new CreatePatoDto();
        createPatoDto.setPatoMae(easyRandom.nextObject(br.com.menesic.GranjaApp.domain.dto.PatoDto.class));
        createPatoDto.setPato(easyRandom.nextObject(br.com.menesic.GranjaApp.domain.dto.PatoDto.class));

        Pato patoMaeSaved = easyRandom.nextObject(Pato.class);
        when(patoRepository.findByNome(createPatoDto.getPatoMae().getNome())).thenReturn(Optional.of(patoMaeSaved));

        Pato patoFilhoSaved = easyRandom.nextObject(Pato.class);
        when(patoRepository.findByNome(createPatoDto.getPato().getNome())).thenReturn(Optional.empty());
        when(patoRepository.save(any(Pato.class))).thenReturn(patoFilhoSaved);

        Pato savedPato = createPatoUseCase.save(createPatoDto);

        verify(patoRepository, times(1)).findByNome(createPatoDto.getPatoMae().getNome());
        verify(patoRepository, times(1)).findByNome(createPatoDto.getPato().getNome());
        verify(patoRepository, times(1)).save(any(Pato.class));
        assertEquals(patoFilhoSaved, savedPato);
    }

    @Test
    public void testSaveShouldThrowEntityExistsException() {
        CreatePatoDto createPatoDto = new CreatePatoDto();
        createPatoDto.setPatoMae(easyRandom.nextObject(br.com.menesic.GranjaApp.domain.dto.PatoDto.class));
        createPatoDto.setPato(easyRandom.nextObject(br.com.menesic.GranjaApp.domain.dto.PatoDto.class));

        Pato patoMaeSaved = easyRandom.nextObject(Pato.class);
        when(patoRepository.findByNome(createPatoDto.getPatoMae().getNome())).thenReturn(Optional.of(patoMaeSaved));

        Pato patoFilho = easyRandom.nextObject(Pato.class);
        when(patoRepository.findByNome(createPatoDto.getPato().getNome())).thenReturn(Optional.of(patoFilho));

        assertThrows(EntityExistsException.class, () -> createPatoUseCase.save(createPatoDto));
    }
}