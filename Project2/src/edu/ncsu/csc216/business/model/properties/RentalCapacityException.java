/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * The RentalCapacityException class defines an exception
 * that is thrown when a specified capacity is exceeded, it
 * extends the Exception class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class RentalCapacityException extends Exception {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The RentalCapacityException Constructor
	 * 
	 * @param message message to be displayed when the 
	 *        exception is thrown
	 */
	public RentalCapacityException(String message) {
		super(message);
	}
}
