/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

/**
 * Throws when the capacity is wrong
 * @author Alex Raum, Walker Clem
 *
 */
public class RentalCapacityException extends Exception {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The exception
	 * @param message message to show
	 */
	public RentalCapacityException(String message) {
		super(message);
	}
}
