package ma.casablanca.ensam.jeeproject.controller;
import ma.casablanca.ensam.jeeproject.dao.entities.Task;
import ma.casablanca.ensam.jeeproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public String allTasks(Model model) {
        List<Task> tasks=taskService.getTasks();
        model.addAttribute("tasks" , tasks);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String taskDetails(@PathVariable Long id, Model model){
        Task task = taskService.getTask(id);
        model.addAttribute("task" , task);
        return "taskDetails";
    }

    @PostMapping("/add")
    public String addTask(Model model , Task task  , RedirectAttributes redirectAttributes){
        Task newTask = taskService.addTask(task);
        if(newTask==null){
            redirectAttributes.addFlashAttribute("message", "Task addition failed");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task has been added successfully");
        model.addAttribute("task" , newTask);
        return "redirect:/tasks";
    }

    @PutMapping("/update")
    public String updateTask(Model model , Task task , RedirectAttributes redirectAttributes){
        Task updatedTask = taskService.updateTask(task);
        if(updatedTask==null){
            redirectAttributes.addFlashAttribute("message", "Task update failed");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task has been updated successfully");
        model.addAttribute("task" , updatedTask);
        return "redirect:/tasks";
    }

    @DeleteMapping("/delete/{id}")
    public String removeTask(@PathVariable Long id ,RedirectAttributes redirectAttributes ){
        boolean isDeleted = taskService.deleteTask(id);
        if(isDeleted){
            redirectAttributes.addFlashAttribute("message", "Task has been deleted successfully");
            return "redirect:/tasks";
        }
        redirectAttributes.addFlashAttribute("message", "Task update failed");
        return "redirect:/tasks";
    }


}
