package br.com.menesic.GranjaApp.domain.adapters.cliente;

import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.port.repository.ClienteRepository;
import br.com.menesic.GranjaApp.domain.port.usecase.cliente.CreateClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateClienteUseCaseImpl implements CreateClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        log.info("[CreateClienteUseCaseImpl] - save");
        Cliente clienteSaved = clienteRepository.save(cliente);
        log.info("[CreateClienteUseCaseImpl] - save: {} SUCCESS ", clienteSaved.getNome());
        return clienteSaved;
    }
}
