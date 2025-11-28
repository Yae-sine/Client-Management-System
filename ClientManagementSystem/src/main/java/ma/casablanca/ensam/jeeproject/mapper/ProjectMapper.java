package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {
    private ModelMapper mapper;

    public ProjectDto toDto(Project project){
        return mapper.map(project , ProjectDto.class);
    }

    public Project toEntity(ProjectDto projectDto){
        return mapper.map(projectDto , Project.class);
    }
}
