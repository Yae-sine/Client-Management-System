package ma.casablanca.ensam.jeeproject.dao.repositories;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByLastName(String name);
}
