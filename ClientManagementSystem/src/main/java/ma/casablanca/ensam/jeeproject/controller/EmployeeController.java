package ma.casablanca.ensam.jeeproject.controller;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.service.EmployeeService;
import ma.casablanca.ensam.jeeproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public String addEmployee(Employee employee, @RequestParam(required = false) Long projectId, Model model, RedirectAttributes redirectAttributes) {
        Employee newEmployee = employeeService.addEmployee(employee);
        if(newEmployee == null) {
            redirectAttributes.addFlashAttribute("message", "Employee addition failed");
            return "redirect:/employees";
        }

        // If a project was selected, assign the employee to it
        if(projectId != null) {
            Project project = projectService.getProject(projectId);
            if(project != null) {
                if(project.getEmployees() == null) {
                    project.setEmployees(new java.util.ArrayList<>());
                }
                project.getEmployees().add(newEmployee);
                projectService.updateProject(project);
            }
        }

        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/employees";
    }

    @GetMapping
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        List<Project> projects = projectService.getProjects();
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        if(employees.isEmpty()) {
            model.addAttribute("message", "No Employees found");
        }
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            redirectAttributes.addFlashAttribute("message", "Employee not found");
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }

    @PutMapping("/update")
    public String updateEmployee(Employee employee, Model model, RedirectAttributes redirectAttributes) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        model.addAttribute("employee", updateEmployee);
        if(updateEmployee == null) {
            redirectAttributes.addFlashAttribute("message", "Employee update failed");
            return "redirect:/employees";
        }
        redirectAttributes.addFlashAttribute("message", "Employee updated successfully!");
        return "redirect:/employees";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Employee deleted successfully!");
            return "redirect:/employees";
        }
        redirectAttributes.addFlashAttribute("message", "Employee delete failed");
        return "redirect:/employees";
    }





}
