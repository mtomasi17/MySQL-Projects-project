package projects.service;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;

//ProjectService class interacts with a ProjectDao object to add a project to a database.

public class ProjectService {
	private static ProjectDao projectDao = new ProjectDao();

	public static Project addProject(Project project) {
		return projectDao.insertProject(project);
	
// ProjectService class acts as an intermediary between the user interface (in the ProjectsApp class) and the data access layer
	}

	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
				"Project with project ID=" + projectId + " does not exist."));
	}

}
