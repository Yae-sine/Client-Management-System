package ma.casablanca.ensam.jeeproject.dto;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;

import java.util.List;

public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String address;
    private List<Project> projects;
}
