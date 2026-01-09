package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dao.repositories.ProjectRepository;
import ma.casablanca.ensam.jeeproject.dto.EmployeeDto;
import ma.casablanca.ensam.jeeproject.dto.ProjectDto;
import ma.casablanca.ensam.jeeproject.mapper.EmployeeMapper;
import ma.casablanca.ensam.jeeproject.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProjectManager implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Optional<ProjectDto> getProject(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDto);
    }

    @Override
    public List<ProjectDto> getProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectByName(String name) {
        Project project = projectRepository.findByName(name);
        return project != null ? projectMapper.toDto(project) : null;
    }

    @Override
    public boolean deleteProject(Long id) {
        projectRepository.deleteById(id);
        return !projectRepository.existsById(id);
    }

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    @Override
    public List<ProjectDto> getProjectsByClientId(Long clientId) {
        return projectRepository.findByClientId(clientId).stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesByProjectId(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.map(p -> p.getEmployees().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
