package projects;

import projects.dao.DbConnection;

public class ProjectsApp {

	//method simply calls the getConnection() method to establish a database connection. 
	public static void main(String[] args) {
		DbConnection.getConnection();

	}

}
