package br.com.menesic.GranjaApp.domain.cliente;

import br.com.menesic.GranjaApp.domain.adapters.cliente.CreateClienteUseCaseImpl;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.ports.repository.ClienteRepository;
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

public class CreateClienteUseCaseImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CreateClienteUseCaseImpl createClienteUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createClienteUseCase = new CreateClienteUseCaseImpl(clienteRepository);
    }

    @Test
    public void testSave() {
        Cliente cliente = easyRandom.nextObject(Cliente.class);

        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente savedCliente = createClienteUseCase.save(cliente);

        verify(clienteRepository, times(1)).save(cliente);
        assertEquals(cliente, savedCliente);
    }

    @Test
    public void testSaveShouldReturnSameCliente() {
        Cliente cliente = easyRandom.nextObject(Cliente.class);

        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente savedCliente = createClienteUseCase.save(cliente);

        assertEquals(cliente, savedCliente);
    }
}
