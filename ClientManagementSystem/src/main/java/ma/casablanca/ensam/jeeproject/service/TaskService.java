package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getTasks();
    public Task getTask(Long id);
    public Task getTaskByTitle(String title);
    public boolean deleteTask(Long id);
    public Task addTask(Task task);
    public Task updateTask(Task task);

}
