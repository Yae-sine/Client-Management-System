package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dto.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDto> getTasks();
    Optional<TaskDto> getTask(Long id);
    boolean deleteTask(Long id);
    TaskDto addTask(TaskDto taskDto);
    TaskDto updateTask(TaskDto taskDto);
}
