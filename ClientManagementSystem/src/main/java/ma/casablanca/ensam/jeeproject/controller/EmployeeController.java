package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import ma.casablanca.ensam.jeeproject.dto.ProjectDto;
import ma.casablanca.ensam.jeeproject.service.EmployeeService;
import ma.casablanca.ensam.jeeproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public String addEmployee(EmployeeDto employeeDto, @RequestParam(required = false) Long projectId, RedirectAttributes redirectAttributes) {
        EmployeeDto newEmployee = employeeService.addEmployeeWithProject(employeeDto, projectId);
        if(newEmployee == null) {
            redirectAttributes.addFlashAttribute("message", "Employee addition failed");
            return "redirect:/employees";
        }
        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/employees";
    }

    @GetMapping
    public String getEmployees(Model model) {
        List<EmployeeDto> employees = employeeService.getEmployees();
        List<ProjectDto> projects = projectService.getProjects();
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        if(employees.isEmpty()) {
            model.addAttribute("message", "No Employees found");
        }
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<EmployeeDto> employee = employeeService.getEmployee(id);
        if (employee.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Employee not found");
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee.get());
        return "employeeDetails";
    }

    @PutMapping("/update")
    public String updateEmployee(EmployeeDto employeeDto, RedirectAttributes redirectAttributes) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto);
        if(updatedEmployee == null) {
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
