package ma.casablanca.ensam.jeeproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id ;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private List<ProjectDto> projects;
}
