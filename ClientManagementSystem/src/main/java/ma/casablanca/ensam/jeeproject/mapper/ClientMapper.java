package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import ma.casablanca.ensam.jeeproject.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    @Autowired
    private ModelMapper mapper;
    public ClientDto toDTO(Client client) {
        return mapper.map(client, ClientDto.class);
    }

    public Client toEntity(ClientDto clientDto) {
        return mapper.map(clientDto, Client.class);
    }
}
