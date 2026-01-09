package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDto> getClients();
    Optional<ClientDto> getClient(Long id);
    ClientDto addClient(ClientDto clientDto);
    ClientDto updateClient(ClientDto clientDto);
    boolean deleteClient(Long id);
}
