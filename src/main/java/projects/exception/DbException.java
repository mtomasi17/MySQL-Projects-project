package projects.exception;

@SuppressWarnings("serial")
public class DbException extends RuntimeException { /*lass provides several constructors that allow you to create instances 
														of the exception with different parameters. */
	
	//The constructor DbException(String message) accepts a message as a parameter and calls the superclass constructor with the provided message.
	public DbException(String message) {
		super(message);
		
	}
	/*The constructor DbException(Throwable cause) accepts a Throwable object (such as an exception or error) as a cause for the
	 exception and calls the superclass constructor with the cause. */
	public DbException(Throwable cause) {
		super(cause);
		
	}
	/*The constructor DbException(String message, Throwable cause) accepts both a message and a cause and calls the superclass
	 constructor with the provided message and cause.*/
	public DbException(String message, Throwable cause) {
		super(message, cause);
		
	}


}
