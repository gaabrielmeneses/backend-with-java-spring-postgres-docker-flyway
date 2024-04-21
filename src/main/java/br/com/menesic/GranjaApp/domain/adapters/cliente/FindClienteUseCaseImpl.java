package br.com.menesic.GranjaApp.domain.adapters.cliente;

import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.ports.repository.ClienteRepository;
import br.com.menesic.GranjaApp.domain.ports.usecase.cliente.FindClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindClienteUseCaseImpl implements FindClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }
}
