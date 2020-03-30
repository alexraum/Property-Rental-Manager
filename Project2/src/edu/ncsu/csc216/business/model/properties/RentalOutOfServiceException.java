/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * @author Alex Raum
 *
 */
public class RentalOutOfServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public RentalOutOfServiceException(String message) {
		super(message);
	}
}
