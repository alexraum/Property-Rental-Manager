/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * Throws if there is a duplicate room
 * @author Alex Raum, Walker Clem
 *
 */
public class DuplicateRoomException extends Exception {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The exception
	 * @param message message to show
	 */
	public DuplicateRoomException(String message) {
		super(message);
	}
}
