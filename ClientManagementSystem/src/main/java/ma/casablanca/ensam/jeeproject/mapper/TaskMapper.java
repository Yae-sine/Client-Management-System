package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.Task;
import ma.casablanca.ensam.jeeproject.dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {
    @Autowired
    private ModelMapper mapper;

    public TaskDto toDto(Task task){
        return mapper.map(task , TaskDto.class);
    }

    public Task toEntity(TaskDto taskDto){
        return mapper.map(taskDto , Task.class);
    }
}
