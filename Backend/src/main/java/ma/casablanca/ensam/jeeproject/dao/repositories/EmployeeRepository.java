package ma.casablanca.ensam.jeeproject.dao.repositories;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
