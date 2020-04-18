/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * The RentalOutOfServiceException class defines an exception
 * that is thrown when a specified rental unit is out of service
 * but attempted to be leased by the user, it extends the Exception
 * class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class RentalOutOfServiceException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The RentalOutOfServiceException Constructor
	 * 
	 * @param message message to be displayed when the
	 *        exception is thrown
	 */
	public RentalOutOfServiceException(String message) {
		super(message);
	}
}
