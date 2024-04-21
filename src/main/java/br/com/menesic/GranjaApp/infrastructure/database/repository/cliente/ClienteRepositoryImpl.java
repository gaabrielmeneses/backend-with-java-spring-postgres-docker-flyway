package br.com.menesic.GranjaApp.infrastructure.database.repository.cliente;

import br.com.menesic.GranjaApp.domain.model.Cliente;
import br.com.menesic.GranjaApp.domain.ports.repository.ClienteRepository;
import br.com.menesic.GranjaApp.infrastructure.database.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteDatabaseRepository databaseRepository;
    private final ModelMapper modelMapper;

    @Override
    public Cliente save(Cliente cliente) {
        log.info("[ClienteRepositoryImpl] - save: {}", cliente.getNome());
        ClienteEntity clienteSaved = databaseRepository.save(modelMapper.map(cliente, ClienteEntity.class));
        return modelMapper.map(clienteSaved, Cliente.class);
    }

    @Override
    public Optional<Cliente> findByNome(String nome) {
        log.info("[PatoRepositoryImpl] - findByNome: {}", nome);
        Optional<ClienteEntity> clienteSaved = databaseRepository.findByNome(nome);
        return Optional.ofNullable(modelMapper.map(clienteSaved, Cliente.class));
    }
}
