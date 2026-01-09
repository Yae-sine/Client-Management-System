package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import ma.casablanca.ensam.jeeproject.dto.ProjectDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<ProjectDto> getProject(Long id);
    List<ProjectDto> getProjects();
    ProjectDto getProjectByName(String name);
    boolean deleteProject(Long id);
    ProjectDto addProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto);
    List<ProjectDto> getProjectsByClientId(Long clientId);
    List<EmployeeDto> getEmployeesByProjectId(Long projectId);
}
