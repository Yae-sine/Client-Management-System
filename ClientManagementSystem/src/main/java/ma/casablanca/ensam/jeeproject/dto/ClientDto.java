package ma.casablanca.ensam.jeeproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String address;
    private String email;
    private List<ProjectDto> projects;
}
