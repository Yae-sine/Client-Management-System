package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.ClientDto;
import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import ma.casablanca.ensam.jeeproject.dto.ProjectDto;
import ma.casablanca.ensam.jeeproject.service.ClientService;
import ma.casablanca.ensam.jeeproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getProjects(Model model){
        List<ProjectDto> projects = projectService.getProjects();
        List<ClientDto> clients = clientService.getClients();
        model.addAttribute("projects", projects);
        model.addAttribute("clients", clients);
        return "projects";
    }

    @GetMapping("/{id}")
    public String getProjectDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<ProjectDto> project = projectService.getProject(id);
        if(project.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Project not found");
            return "redirect:/projects";
        }
        model.addAttribute("project", project.get());
        return "projectDetails";
    }

    @PutMapping("/update")
    public String updateProject(ProjectDto projectDto, RedirectAttributes redirectAttributes){
        ProjectDto updatedProject = projectService.updateProject(projectDto);
        if(updatedProject == null){
            redirectAttributes.addFlashAttribute("message", "Project update failed");
            return "redirect:/projects";
        }
        redirectAttributes.addFlashAttribute("message", "Project updated successfully!");
        return "redirect:/projects";
    }

    @DeleteMapping("/delete")
    public String deleteProject(@RequestParam(name = "id") Long id, RedirectAttributes redirectAttributes){
        boolean isDeleted = projectService.deleteProject(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Project deleted successfully!");
            return "redirect:/projects";
        }
        redirectAttributes.addFlashAttribute("message", "Project delete failed");
        return "redirect:/projects";
    }

    @PostMapping("/add")
    public String addProject(ProjectDto projectDto, RedirectAttributes redirectAttributes){
        ProjectDto newProject = projectService.addProject(projectDto);
        if(newProject == null){
            redirectAttributes.addFlashAttribute("message", "Project creation failed");
            return "redirect:/projects";
        }
        redirectAttributes.addFlashAttribute("message", "Project created successfully!");
        return "redirect:/projects";
    }

    @GetMapping("/byClient/{clientId}")
    public String getProjectsByClientId(@PathVariable Long clientId, Model model) {
        List<ProjectDto> projects = projectService.getProjectsByClientId(clientId);
        model.addAttribute("projects", projects);
        if(projects.isEmpty()){
            model.addAttribute("message", "No Project was found");
        }
        return "projects";
    }

    @GetMapping("{id}/employees")
    public String getEmployeesByProjectId(@PathVariable Long id, Model model){
        List<EmployeeDto> employees = projectService.getEmployeesByProjectId(id);
        model.addAttribute("employees", employees);
        if(employees.isEmpty()){
            model.addAttribute("message", "No Employees found for this project");
        }
        return "projectEmployees";
    }

}
