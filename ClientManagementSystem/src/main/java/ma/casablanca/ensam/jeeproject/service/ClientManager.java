package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import ma.casablanca.ensam.jeeproject.dao.repositories.ClientRepository;
import ma.casablanca.ensam.jeeproject.dto.ClientDto;
import ma.casablanca.ensam.jeeproject.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ClientManager implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> getClient(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO);
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDTO(updatedClient);
    }

    @Override
    public boolean deleteClient(Long id) {
        clientRepository.deleteById(id);
        return !clientRepository.existsById(id);
    }
}
