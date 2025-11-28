package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Employee;
import ma.casablanca.ensam.jeeproject.dao.entities.Project;
import ma.casablanca.ensam.jeeproject.dao.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProjectManager implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }


    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public boolean deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()) {
            projectRepository.delete(project.get());
            return true;
        }
        return false;
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getProjectsByClientId(Long clientId) {
        return projectRepository.findByClientId(clientId);
    }

    @Override
    public List<Employee> getEmployeesByProjectId(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.map(Project::getEmployees).orElse(null);
    }
}
