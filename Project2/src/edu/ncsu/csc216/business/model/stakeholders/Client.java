/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.model.contracts.Lease;

/**
 * @author Alex Raum
 *
 */
public class Client {
	
	/** */
	private String name;
	/** */
	private String id;
	/** */
	private SimpleArrayList myLeases;
	
	/**
	 * 
	 * @param name
	 * @param id
	 */
	public Client(String name, String id) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	/**
	 * 
	 * @param l
	 */
	public void addNewLease(Lease l) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] listLeases() {
		return null;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Lease cancelLeaseAt(int index) {
		return null;
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public Lease cancelLeaseWithNumber(int number) {
		return null;
	}
}
