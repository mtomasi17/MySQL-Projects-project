"# mySQLWeek7Exercises" 

Project Description:
The MySQL Projects project combines the use of many different resources to accomplish the goal of creating a database and an interface to interact
with it.  The use of Draw.IO was utilized to create an ERD (Entity Relation Diagram) to assist in visualizing how the tables were to be set up.  MySQL was utilized to greate the tables and populate them with given data.  Java was utilized to create an interface tbat could make contact with MySQL to interect with the database.  

Technologies Used:  
Java
Maven
MySQL
Draw.io

Highlights:
This was a project that took several weeks to complete folloing the guidelines given by Promineo Tech.  There was many parts in the project where I got stuck
and unable to figure out how to solve issues.  Thankfully with the help of mentors, I was able to get the projects to function properly

Code Snippets:
(ProjectsApp.java)

	private void processUserSelections() {
		boolean done = false;

		while (!done) {
			try {
				int selection = getUserSelection();

				switch (selection) {
				case -1:
					done = exitMenu();
					break;

				case 1:
					createProject();
					break;
					
				case 2:
					listProjects();
					break;
					
				case 3:
					selectProject();
					break;
					
				case 4:
					updateProjectDetails();
					break;
					
				case 5:
					deleteProject();
					break;

				default:
					System.out.println("\n" + selection + " is not a valid selection.  Try again");
					break;
				}

			} 



