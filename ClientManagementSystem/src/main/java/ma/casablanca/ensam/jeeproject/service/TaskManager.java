package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Task;
import ma.casablanca.ensam.jeeproject.dao.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskManager implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).get();

    }

    @Override
    public Task getTaskByTitle(String title) {
        return taskRepository.findTaskByTitle(title);
    }

    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            taskRepository.delete(task.get());
            return true;
        }
        return false;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
