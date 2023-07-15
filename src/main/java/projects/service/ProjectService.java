package projects.service;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

//ProjectService class interacts with a ProjectDao object to add a project to a database.

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();

	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	
// ProjectService class acts as an intermediary between the user interface (in the ProjectsApp class) and the data access layer
	}

	/*his method acts as a bridge between the projectService and the projectDao
	 *  and returns the resulting list of projects to the caller.
	 */
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	/*method serves as a layer between the projectService and projectDao and delegates
	 *  the responsibility of fetching a project by its ID and returns the project if it exists.
	 */
	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
				"Project with project ID=" + projectId + " does not exist."));
	}
	
	/*
	 *  the modifyProjectDetails() method calls the corresponding method in the projectDao object to modify
	 *   the project details. If the modification fails, it throws a custom exception.
	 */

	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		}
		
	}
	
	/*
	 * the deleteProject() method calls the corresponding method in the projectDao object to delete the 
	 * project based on its ID. If the deletion fails, it throws a custom exception.
	 */

	public void deleteProject(Integer projectId) {
		if(!projectDao.deleteProject(projectId)) {
			throw new DbException("Project with ID=" + projectId + " does not exist.");
		}
		
	}

}
