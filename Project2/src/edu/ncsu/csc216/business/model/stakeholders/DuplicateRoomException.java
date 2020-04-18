/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * The DuplicateRoomException class defines an exception
 * that is thrown when a specified RentalUnit already exists, 
 * it extends the Exception class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class DuplicateRoomException extends Exception {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The DuplicateRoomException Constructor
	 * 
	 * @param message message to be displayed when the 
	 *        exception is thrown
	 */
	public DuplicateRoomException(String message) {
		super(message);
	}
}
