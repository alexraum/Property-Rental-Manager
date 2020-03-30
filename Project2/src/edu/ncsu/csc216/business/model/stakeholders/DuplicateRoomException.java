/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * @author Alex Raum
 *
 */
public class DuplicateRoomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public DuplicateRoomException(String message) {
		super(message);
	}
}
