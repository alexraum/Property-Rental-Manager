/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * Throws if there is a duplicate client
 * @author Alex Raum, Walker Clem
 *
 */
public class DuplicateClientException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The exception
	 * @param message message to show
	 */
	public DuplicateClientException(String message) {
		super(message);
	}
}
