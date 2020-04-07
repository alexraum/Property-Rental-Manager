/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.model.contracts.Lease;

/**
 * The Client class represents a client of the business, each 
 * Client can have multiple leases at one time.
 * 
 * @author Alex Raum, Walker Clem
 */
public class Client {
	
	/** name of client */
	private String name;
	/** id of client */
	private String id;
	/** leases for client */
	private SimpleArrayList<Lease> myLeases;
	
	/**
	 * Client constructor
	 * 
	 * @param name of client
	 * @param id of client
	 */
	public Client(String name, String id) {
		if (name.isBlank()) {
			throw new IllegalArgumentException();
		}
		if (id.length() < 3) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.id = id;
		myLeases = new SimpleArrayList<Lease>();
	}
	
	/**
	 * Gets the name of Client
	 * 
	 * @return the name of the Client
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the id of Client
	 * 
	 * @return the Clients id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Hashcode
	 * 
	 * @return the hash
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Checks two Client objects for equality by comparing
	 * their id's.
	 * 
	 * @param o the object to compare this Client
	 * @return true if the id's are the same
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Client) {
			Client client = (Client) o;
			return getId() == client.getId();
		}
		return false;
	}
	
	/**
	 * Adds a new Lease to the list of Leases
	 * 
	 * @param lease the Lease to be added
	 */
	public void addNewLease(Lease lease) {
		if (!equals(lease.getClient())) {
			throw new IllegalArgumentException();
		}
		myLeases.add(lease);
	}
	
	/**
	 * Returns an array of Strings, where each String represents a 
	 * Lease for the Client
	 * 
	 * @return an array of Strings containing details of each Lease
	 */
	public String[] listLeases() {
		// TODO need to check if interpretation of implementation is correct
		String[] leases = new String[myLeases.size()];
		for (int i = 0; i < myLeases.size(); i++) {
			Lease lease = myLeases.get(i);
			leases[i] = "" + lease.getConfirmationNumber() + lease.getStart()
			+ lease.getEnd() + lease.getNumOccupants() + getName() + getId();
		}
		return leases;
	}
	
	/**
	 * Cancels the Lease in the myLeases field at the specified 
	 * index, returns that Lease to the user.
	 * 
	 * @param index index in the myLeases field of the Lease to 
	 * be canceled
	 * @return the canceled Lease
	 */
	public Lease cancelLeaseAt(int index) {
		if (index < 0 || index >= this.myLeases.size()) {
			throw new IllegalArgumentException();
		}
		// TODO cancel this Lease in the RentalUnit myLeases list
		return this.myLeases.remove(index);
	}
	
	/**
	 * Cancels the Lease in the myLeases field whose confirmationNumber
	 * matches the confirmationNumber search parameter.
	 * 
	 * @param confirmationNumber the confirmationNumber of the desired Lease 
	 * in the myLeases field to be canceled
	 * @return the canceled Lease
	 */
	public Lease cancelLeaseWithNumber(int confirmationNumber) {
		for (int i = 0; i < myLeases.size(); i++) {
			if (myLeases.get(i).getConfirmationNumber() == confirmationNumber) {
				return cancelLeaseAt(i);
			} 
		}
		throw new IllegalArgumentException();
	}
}
