package ma.casablanca.ensam.jeeproject.dto;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;
import ma.casablanca.ensam.jeeproject.dao.entities.ProjectStatus;
import ma.casablanca.ensam.jeeproject.dao.entities.Task;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDto {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus status;
    private Long budget;
    private Long clientId;
    private List<Task> tasks;
    private List<Invoice> invoices;
    private List<Employee> employees;
}
