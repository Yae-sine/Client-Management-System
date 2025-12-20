package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.service.ClientService;
import ma.casablanca.ensam.jeeproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getProjects(Model model){
        List<Project> projects =projectService.getProjects();
        List<Client> clients = clientService.getClients();
        model.addAttribute("projects",projects);
        model.addAttribute("clients", clients);
        return "projects";
    }

    @GetMapping("/{id}")
    public String getProjectDetails(@PathVariable Long id , Model model , RedirectAttributes redirectAttributes ){
        Project project = projectService.getProject(id);
        if(project == null){
            redirectAttributes.addFlashAttribute("message","Project not found");
            return "redirect:/projects";
        }
        model.addAttribute("project",project);
        return "projectDetails";
    }
    @PutMapping("/update")
    public String updateProject(Model model , Project project , RedirectAttributes redirectAttributes ){
        Project updatedProject = projectService.updateProject(project);
        model.addAttribute("project",updatedProject);
        redirectAttributes.addFlashAttribute("message","Project updated successfully!");
        return "redirect:/projects";
    }

    @DeleteMapping("/delete")
    public String deleteProject(@RequestParam(name = "id") Long id , RedirectAttributes redirectAttributes){
        boolean isDeleted = projectService.deleteProject(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Project deleted successfully!");
            return "redirect:/projects";
        }
        redirectAttributes.addFlashAttribute("message", "Project delete failed");
        return "redirect:/projects";
    }

    @PostMapping("/add")
    public String addProject(Model model , Project project , RedirectAttributes redirectAttributes){
        Project newProject = projectService.addProject(project);
        model.addAttribute("project",newProject);
        return "redirect:/projects";
    }

    @GetMapping("/byClient/{clientId}")
    public String getProjectsByClientId(@PathVariable Long clientId , Model model ) {
        List<Project> projects = projectService.getProjectsByClientId(clientId);
        model.addAttribute("projects", projects);
        if(projects.isEmpty()){
            model.addAttribute("message","No Project was found");
        }
        return "projects";
    }

    @GetMapping("{id}/employees")
    public String getEmployeesByProjectId(@PathVariable Long id , Model model){
        List<Employee> employees = projectService.getEmployeesByProjectId(id);
        model.addAttribute("employees",employees);
        if(employees.isEmpty()){
            model.addAttribute("message","No Employees found for this project");
        }
        return "projectEmployees";
    }

}
