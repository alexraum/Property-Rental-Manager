/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * Throws when the date is wrong
 * @author Alex Raum, Walker Clem
 *
 */
public class RentalDateException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The exception
	 * @param message message to show
	 */
	public RentalDateException(String message) {
		super(message);
	}
}
