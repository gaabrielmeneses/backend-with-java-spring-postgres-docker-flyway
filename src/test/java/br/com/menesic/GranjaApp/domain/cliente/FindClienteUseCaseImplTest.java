package br.com.menesic.GranjaApp.domain.cliente;

import br.com.menesic.GranjaApp.domain.adapters.cliente.FindClienteUseCaseImpl;
import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.ports.repository.ClienteRepository;
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
public class FindClienteUseCaseImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private FindClienteUseCaseImpl findClienteUseCase;

    private static EasyRandom easyRandom;

    @BeforeAll
    private static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        findClienteUseCase = new FindClienteUseCaseImpl(clienteRepository);
    }

    @Test
    public void testFindByNome() {
        String nome = "Gabriel Menesic";
        Cliente cliente = easyRandom.nextObject(Cliente.class);

        when(clienteRepository.findByNome(nome)).thenReturn(Optional.of(cliente));
        Optional<Cliente> foundCliente = findClienteUseCase.findByNome(nome);

        verify(clienteRepository, times(1)).findByNome(nome);
        assertEquals(cliente, foundCliente.orElse(null));
    }

    @Test
    public void testFindByNomeNotFound() {
        String nome = "Gabriel Nascimento";

        when(clienteRepository.findByNome(nome)).thenReturn(Optional.empty());
        Optional<Cliente> foundCliente = findClienteUseCase.findByNome(nome);

        verify(clienteRepository, times(1)).findByNome(nome);
        assertEquals(Optional.empty(), foundCliente);
    }
}