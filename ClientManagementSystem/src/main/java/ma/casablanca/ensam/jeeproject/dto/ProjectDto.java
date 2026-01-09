package ma.casablanca.ensam.jeeproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;
import ma.casablanca.ensam.jeeproject.dao.entities.ProjectStatus;
import ma.casablanca.ensam.jeeproject.dao.entities.Task;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus status;
    private Long budget;
    private String description;
    private ClientDto client;
    private List<TaskDto> tasks;
    private List<InvoiceDto> invoices;
    private List<EmployeeDto> employees;
}
