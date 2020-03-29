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
	 * @param message
	 */
	public DuplicateRoomException(String message) {
		super(message);
	}
}
