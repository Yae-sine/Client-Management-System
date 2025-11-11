package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dao.entities.User;

import java.util.List;

public interface EmployeeService {
    public Employee  addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public boolean deleteEmployee(Long id);
    public Employee getEmployee(Long id);
    public Employee getEmployeeByLastName(String name);
    public List<Employee> getEmployees();
    public List<Employee> getEmployeesByProject(Project project);
}
