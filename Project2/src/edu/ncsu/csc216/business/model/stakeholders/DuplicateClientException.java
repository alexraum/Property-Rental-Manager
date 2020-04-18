/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * The DuplicateClientException class defines an exception
 * that is thrown when a specified Client already exists, it
 * extends the Exception class.
 * 
 * @author Alex Raum, Walker Clem
 */
public class DuplicateClientException extends Exception {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The DuplicateClientException Constructor
	 * 
	 * @param message message to be displayed when the 
	 *        exception is thrown
	 */
	public DuplicateClientException(String message) {
		super(message);
	}
}
