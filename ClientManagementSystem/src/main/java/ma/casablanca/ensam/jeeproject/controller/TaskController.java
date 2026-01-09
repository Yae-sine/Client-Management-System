package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.ProjectDto;
import ma.casablanca.ensam.jeeproject.dto.TaskDto;
import ma.casablanca.ensam.jeeproject.service.ProjectService;
import ma.casablanca.ensam.jeeproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String allTasks(Model model) {
        List<TaskDto> tasks = taskService.getTasks();
        List<ProjectDto> projects = projectService.getProjects();
        model.addAttribute("tasks", tasks);
        model.addAttribute("projects", projects);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String taskDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<TaskDto> task = taskService.getTask(id);
        if(task.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Task not found");
            return "redirect:/tasks";
        }
        model.addAttribute("task", task.get());
        return "taskDetails";
    }

    @PostMapping("/add")
    public String addTask(TaskDto taskDto, RedirectAttributes redirectAttributes){
        TaskDto newTask = taskService.addTask(taskDto);
        if(newTask == null){
            redirectAttributes.addFlashAttribute("message", "Task addition failed");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task has been added successfully");
        return "redirect:/tasks";
    }

    @PutMapping("/update")
    public String updateTask(TaskDto taskDto, RedirectAttributes redirectAttributes){
        TaskDto updatedTask = taskService.updateTask(taskDto);
        if(updatedTask == null){
            redirectAttributes.addFlashAttribute("message", "Task update failed");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task has been updated successfully");
        return "redirect:/tasks";
    }

    @DeleteMapping("/delete/{id}")
    public String removeTask(@PathVariable Long id, RedirectAttributes redirectAttributes){
        boolean isDeleted = taskService.deleteTask(id);
        if(isDeleted){
            redirectAttributes.addFlashAttribute("message", "Task has been deleted successfully");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task deletion failed");
        return "redirect:/tasks";
    }

}
