package ma.casablanca.ensam.jeeproject.service;


import ma.casablanca.ensam.jeeproject.dao.entities.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getClients();
    public  Client findClientByEmail(String email);
    public Client findClientByFirstName(String firstName);
    public Client findCLientByLastName(String lastName);
    public Client getClient(Long id);
    public Client addClient(Client client);
    public Client updateClient(Client client);
    public boolean deleteClient(Long id);
}
