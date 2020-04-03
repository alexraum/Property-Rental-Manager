/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * Throws when the room is out of service
 * @author Alex Raum, Walker Clem
 *
 */
public class RentalOutOfServiceException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The exception
	 * @param message message to show
	 */
	public RentalOutOfServiceException(String message) {
		super(message);
	}
}
