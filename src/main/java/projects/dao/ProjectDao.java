package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects.entity.Project;
import projects.exception.DbException;
import provided.util.DaoBase;

// ProjectDao class is responsible for inserting a Project object into the database
// The class defines several constant variables representing table names in the database
// These variables are used in the SQL statements to reference the corresponding tables

public class ProjectDao extends DaoBase {
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";

// The insertProject method takes a Project object as a parameter and returns a Project object	

	public Project insertProject(Project project) {
		
// The method prepares an SQL statement to insert the project into the PROJECT_TABLE
		
		//@formatter:off
		
		String sql = ""
		+ "INSERT INTO " + PROJECT_TABLE + " "
		+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
		+ "VALUES "
		+ "(?, ?, ?, ?, ?)";
		
		//@formatter:on

/* The method obtains a connection from the DbConnection.getConnection 
 * method and starts a transaction using the startTransaction method 
 */
		try (Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);

/* Within the transaction, the method creates a prepared statement from the 
 * SQL statement and sets the parameter values using the setParameter
 */
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class);

// The statement is executed using executeUpdate to perform the insertion into the database				

				stmt.executeUpdate();
				
// After the successful insertion, the method retrieves the last inserted ID using the getLastInsertId method
// The transaction is then committed using the commitTransaction method 	
				
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				commitTransaction(conn);

				project.setProjectId(projectId);
				return project;

			}

/* If any exception occurs during the execution or transaction handling, 
 * the transaction is rolled back using the rollbackTransaction method
 */

			catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

}
