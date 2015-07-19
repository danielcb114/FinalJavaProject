package edu.ncsu.csc216.movie101.util;

/**
 * EmptyQuestionListException specifies behaviors required for throwing
 * the exception if there is no question of the appropriate type to
 * present. This exception, if thrown, tells the program to run the
 * quits execution.
 * 
 * @author Kaleb
 */
public class EmptyQuestionListException extends Exception {
	
	/** The default error message */
	public static final String MESSAGE = "Empty Question List";
	/** The default Serial Version ID */
	public static final long serialVersionUID = 1L;
	
	/**
	 * The default constructor that uses the default error
	 * message.
	 */
	public EmptyQuestionListException() {
		super(MESSAGE);
	}
	
	/**
	 * The unique constructor that uses the parameter
	 * message as the message for the exception.
	 * @param message the string of the error message
	 */
	public EmptyQuestionListException(String message) {
		super(message);
	}
	

}
