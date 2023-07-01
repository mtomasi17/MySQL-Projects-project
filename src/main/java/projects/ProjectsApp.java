package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

// Program allows users to add projects to a project management system

public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);

	// @formatter:off
	private List<String> operations = List.of(
		"1) Add a project"
	);
	// @formatter:on

	
// ProjectsApp class is created, and the processUserSelections method is called to handle user input and menu navigation
	
	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();

	}

// processUserSelections method contains a loop that continues until the user chooses to exit the program
// The available menu options are defined in the operations list

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

				default:
					System.out.println("\n" + selection + " is not a valid selection.  Try again");
					break;
				}

			} catch (Exception e) {
				System.out.println("\nError: " + e + "Try again.");
			}
		}

	}

/*
 * The createProject method prompts the user to input various project details such as name, estimated hours, actual hours,
 * difficulty, and notes.  It then creates a Project object. The newly created project is then displayed to the user
 */
	
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");

		Project project = new Project();

		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);

		Project dbProject = ProjectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);

	}
	
	
//getDecimalInput prompts the user for a decimal number input and converts it to a BigDecimal value.
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return new BigDecimal(input).setScale(2);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}

	}

	private boolean exitMenu() {
		System.out.println("Exiting the menue.");
		return true;

	}

	private int getUserSelection() {
		printOperations();

		Integer input = getIntInput("Enter a menu selection");

		return Objects.isNull(input) ? -1 : input;

	}

// getIntInput prompts the user for an integer input and converts it to an Integer value.
	
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}

	}
	
// getStringInput prompts the user for a string input.	

	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();

		return input.isBlank() ? null : input.trim();
	}

// printOperations displays the available menu options to the user.
	
	private void printOperations() {
		System.out.println("\nThese are the available selections.  Press the enter key to quit:");

		operations.forEach(line -> System.out.println("  " + line));

	}

}
