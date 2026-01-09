package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto addEmployeeWithProject(EmployeeDto employeeDto, Long projectId);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    boolean deleteEmployee(Long id);
    Optional<EmployeeDto> getEmployee(Long id);
    EmployeeDto getEmployeeByLastName(String name);
    List<EmployeeDto> getEmployees();
}
