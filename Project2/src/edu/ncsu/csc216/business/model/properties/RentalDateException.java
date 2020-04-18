/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * The RentalDateException class defines an exception
 * that is thrown when a provided date is invalid, it
 * extends the Exception class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class RentalDateException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The RentalDateException Constructor
	 * 
	 * @param message message to be displayed when the 
	 *        exception is thrown
	 */
	public RentalDateException(String message) {
		super(message);
	}
}
