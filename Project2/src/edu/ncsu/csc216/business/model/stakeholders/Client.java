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
	 * Creates a unique hash code for the Client
	 * 
	 * @return the hash code for the Client
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((myLeases == null) ? 0 : myLeases.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * Compares the Client to another object to determine if they are equal.
	 * 
	 * @return boolean representing if the Client is equal to the object
	 * that it was compared to
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		// may need to change later
		if (myLeases == null) {
			if (other.myLeases != null)
				return false;
		} else if (!myLeases.equals(other.myLeases))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
//		String[] leases = new String[myLeases.size()];
//		for (int i = 0; i < myLeases.size(); i++) {
//			Lease lease = myLeases.get(i);
//			leases[i] = lease.getConfirmationNumber() + " | " + lease.getStart()
//			+ " to " + lease.getEnd() + " | " + lease.getNumOccupants() + " | " 
//			+ getName() + " " + getId();
//		}
//		return leases;
		String[] leases = new String[myLeases.size()];
		for (int i = 0; i < myLeases.size(); i++) {
			String[] data = myLeases.get(i).leaseData();
			leases[i] = data[0] + " | " + data[1] + " | " 
			+ data[2] + " | " + data[4] + " " + data[5];
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
