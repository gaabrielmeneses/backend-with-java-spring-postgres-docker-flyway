package br.com.menesic.GranjaApp.domain.venda;

import br.com.menesic.GranjaApp.domain.adapters.venda.CreateVendaUseCaseImpl;
import br.com.menesic.GranjaApp.domain.dto.ClienteDto;
import br.com.menesic.GranjaApp.domain.dto.PatoDto;
import br.com.menesic.GranjaApp.domain.dto.VendaDto;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.model.Pato;
import br.com.menesic.GranjaApp.domain.model.Venda;
import br.com.menesic.GranjaApp.domain.ports.repository.VendaRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.cliente.FindClienteUseCase;
import br.com.menesic.GranjaApp.domain.ports.usecase.pato.FindPatoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
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

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class CreateVendaUseCaseImplTest {

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private FindPatoUseCase findPatoUseCase;

    @Mock
    private FindClienteUseCase findClienteUseCase;

    @InjectMocks
    private CreateVendaUseCaseImpl createVendaUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createVendaUseCase = new CreateVendaUseCaseImpl(vendaRepository, findPatoUseCase, findClienteUseCase);
    }

    @Test
    public void testSave() {
        VendaDto vendaDto = new VendaDto();
        Cliente cliente = easyRandom.nextObject(Cliente.class);
        ClienteDto clienteDto = new ClienteDto(cliente.getNome(), cliente.getDesconto());

        vendaDto.setCliente(clienteDto);

        PatoDto patoDto = easyRandom.nextObject(PatoDto.class);
        patoDto.setNome("Pato 1");
        patoDto.getMae().setNome("Mãe");

        vendaDto.setPato(Arrays.asList(patoDto));

        Pato mae = easyRandom.nextObject(Pato.class);
        mae.setNome(patoDto.getMae().getNome());
        Pato pato = new Pato(UUID.randomUUID(), patoDto.getNome(), patoDto.getValor(), patoDto.getVendido(), mae);

        when(findClienteUseCase.findByNome(cliente.getNome())).thenReturn(Optional.of(cliente));
        when(findPatoUseCase.findByNome("Pato 1")).thenReturn(Optional.of(pato));

        createVendaUseCase.save(vendaDto);

        verify(vendaRepository, times(1)).save(any(Venda.class));
    }

    @Test
    public void testSaveWithUnknownCliente() {
        VendaDto vendaDto = new VendaDto();
        Cliente cliente = easyRandom.nextObject(Cliente.class);
        ClienteDto clienteDto = new ClienteDto(cliente.getNome(), cliente.getDesconto());

        vendaDto.setCliente(clienteDto);

        PatoDto patoDto = easyRandom.nextObject(PatoDto.class);
        patoDto.setNome("Pato 1");
        patoDto.getMae().setNome("Mãe");

        vendaDto.setPato(Arrays.asList(patoDto));

        Pato mae = easyRandom.nextObject(Pato.class);
        mae.setNome(patoDto.getMae().getNome());

        when(findClienteUseCase.findByNome(cliente.getNome())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> createVendaUseCase.save(vendaDto));
    }

    @Test
    public void testSaveWithUnknownPato() {
        VendaDto vendaDto = new VendaDto();
        Cliente cliente = easyRandom.nextObject(Cliente.class);
        ClienteDto clienteDto = new ClienteDto(cliente.getNome(), cliente.getDesconto());

        vendaDto.setCliente(clienteDto);

        PatoDto patoDto = easyRandom.nextObject(PatoDto.class);
        patoDto.setNome("Pato 1");
        patoDto.getMae().setNome("Mãe");

        vendaDto.setPato(Arrays.asList(patoDto));

        when(findClienteUseCase.findByNome(cliente.getNome())).thenReturn(Optional.of(cliente));
        when(findPatoUseCase.findByNome("Pato 2")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> createVendaUseCase.save(vendaDto));
    }
}