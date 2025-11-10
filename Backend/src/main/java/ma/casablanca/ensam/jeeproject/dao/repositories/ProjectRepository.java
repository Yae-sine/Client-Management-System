package ma.casablanca.ensam.jeeproject.dao.repositories;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
