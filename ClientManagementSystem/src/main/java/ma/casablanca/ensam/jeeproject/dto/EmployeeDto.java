package ma.casablanca.ensam.jeeproject.dto;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;

import java.util.List;

public class EmployeeDto {
    private Long id ;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private List<Project> projects;
}
