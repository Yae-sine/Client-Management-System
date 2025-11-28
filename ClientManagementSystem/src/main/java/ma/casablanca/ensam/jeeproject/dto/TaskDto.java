package ma.casablanca.ensam.jeeproject.dto;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dao.entities.TaskStatus;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long projectId;

}
