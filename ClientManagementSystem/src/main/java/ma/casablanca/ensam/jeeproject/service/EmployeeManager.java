package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dao.repositories.EmployeeRepository;
import ma.casablanca.ensam.jeeproject.dao.repositories.ProjectRepository;
import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import ma.casablanca.ensam.jeeproject.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeManager implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto addEmployeeWithProject(EmployeeDto employeeDto, Long projectId) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        if (projectId != null) {
            Optional<Project> project = projectRepository.findById(projectId);
            if (project.isPresent()) {
                project.get().getEmployees().add(savedEmployee);
                projectRepository.save(project.get());
            }
        }

        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.existsById(id);
    }

    @Override
    public Optional<EmployeeDto> getEmployee(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDto getEmployeeByLastName(String lastName) {
        Employee employee = employeeRepository.findByLastName(lastName);
        return employee != null ? employeeMapper.toDto(employee) : null;
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
