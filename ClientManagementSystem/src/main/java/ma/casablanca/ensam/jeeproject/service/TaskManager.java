package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Task;
import ma.casablanca.ensam.jeeproject.dao.repositories.TaskRepository;
import ma.casablanca.ensam.jeeproject.dto.TaskDto;
import ma.casablanca.ensam.jeeproject.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class TaskManager implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> getTask(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto);
    }

    @Override
    public boolean deleteTask(Long id) {
        taskRepository.deleteById(id);
        return !taskRepository.existsById(id);
    }

    @Override
    public TaskDto addTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }
}
