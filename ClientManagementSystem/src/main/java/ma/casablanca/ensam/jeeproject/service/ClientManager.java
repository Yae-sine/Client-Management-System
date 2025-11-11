package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import ma.casablanca.ensam.jeeproject.dao.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientManager implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client findClientByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    @Override
    public Client findCLientByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).get();
    }
}
