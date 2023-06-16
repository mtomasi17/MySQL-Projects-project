package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;

public class DbConnection { /*DbConnection class is responsible for establishing a database 
	connection using using JDBC (Java Database Connectivity) for a MySQL database. */

	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
//Defines private static fields (HOST, PASSWORD, PORT, SCHEMA, and USER) that hold the connection parameters.
	
	public static Connection getConnection() { //getConnection() method returns a Connection object, representing the connection to the MySQL database.
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", 
				HOST, PORT, SCHEMA, USER, PASSWORD); //connection URI is constructed using the provided connection parameters.
		
		/*Inside a try-catch block, the DriverManager.getConnection() method is called with the constructed URI
		to establish a connection to the database.*/
		
		try {
			Connection conn = DriverManager.getConnection(uri);//If the connection is successfully established, a success message is printed
			System.out.println("Connection to schema " + SCHEMA + " is Successful.");
			return conn; // returns connection if successful
		} catch (SQLException e) { //If an SQLException thrown during connection process, error message is printed to the console,DbException is thrown.
			System.out.println("Unable to get connection at " + uri);
			throw new DbException("Unable to get connection at " + uri);
			
		}
	}
}
