package ma.casablanca.ensam.jeeproject.dao.repositories;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    User findByUsername(String username);
}
