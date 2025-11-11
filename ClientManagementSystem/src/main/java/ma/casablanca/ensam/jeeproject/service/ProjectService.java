package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Project;

import java.util.List;

public interface ProjectService {
    public Project getProject(Long id);
    public List<Project> getProjects();
    public Project getProjectByName(String name);
    public boolean deleteProject(Long id);
    public Project addProject(Project project);
    public Project updateProject(Project project);
    public List<Project> getProjectsByClientId(Long clientId);

}
