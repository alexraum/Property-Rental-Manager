/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.model.contracts.Lease;

/**
 * The client object
 * @author Alex Raum, Walker Clem
 *
 */
public class Client {
	
	/** name of client */
	private String name;
	/** id of client */
	private String id;
	/** leases for client */
	private SimpleArrayList myLeases;
	
	/**
	 * Client constructor
	 * @param name of client
	 * @param id of client
	 */
	public Client(String name, String id) {
//		if (name.isBlank()) {
//			throw new IllegalArgumentException();
//		}
//		if (id.length() < 3) {
//			throw new IllegalArgumentException();
//		}
//		this.name = name;
//		this.id = id;
//		myLeases = new SimpleArrayList<Lease>();
//		if (myLeases.contains(id)) {
//			throw new DuplicateClientException("Client already added");
//		}
//		
	}
	
	/**
	 * Gets the name of client
	 * @return the name
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * Gets the id of client
	 * @return the id
	 */
	public String getId() {
		return null;
	}
	
	/**
	 * Hashcode
	 * @return the hash
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Equals
	 * @param o the object to compare
	 * @return if equals
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	/**
	 * Adds a new lease
	 * @param l the lease
	 */
	public void addNewLease(Lease l) {
		myLeases.add(l);
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
