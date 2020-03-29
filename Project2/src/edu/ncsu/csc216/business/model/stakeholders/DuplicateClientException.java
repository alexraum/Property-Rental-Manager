/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

/**
 * @author Alex Raum
 *
 */
public class DuplicateClientException extends Exception {

	/**
	 * 
	 * @param message
	 */
	public DuplicateClientException(String message) {
		super(message);
	}
}
