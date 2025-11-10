package ma.casablanca.ensam.jeeproject.dao.repositories;

import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client , Long> {
}
