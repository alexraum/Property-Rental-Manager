/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * @author Alex Raum, Walker Clem
 *
 */
public abstract class RentalUnit {

	/** the max floor */
	public static final int MAX_FLOOR = 45;
	/** the minimum floor */
	public static final int MIN_FLOOR = 1;
	/** the max room */
	public static final int MAX_ROOM = 99;
	/** the min room */
	public static final int MIN_ROOM = 10;
	/** if in service */
	private boolean inService;
	/** the floor number */
	private int floor;
	/** the room number*/
	private int room;
	/** the room capacity */
	private int capacity;
	/** the leases list */
	protected SortedLinkedListWithIterator myLeases;
	
	/**
	 * Constructor for the Rental unit
	 * @param s the name of the room
	 * @param i the id of the room
	 */
	public RentalUnit(String s, int i) {
		
	}
	
	/**
	 * Gets the capacity of the room
	 * @return the capacity
	 */
	public int getCapacity() {
		return 0;
	}
	
	/**
	 * Gets the floor num
	 * @return floor num
	 */
	public int getFloor() {
		return 0;
	}
	
	/**
	 * Gets the room num
	 * @return room num
	 */
	public int getRoom() {
		return 0;
	}
	
	/**
	 * Compares 2 units
	 * @param unit the unit to compare to
	 * @return which one comes first
	 */
	public int compareTo(RentalUnit unit) {
		return 0;
	}
	
	/**
	 * Returns the room to service
	 */
	public void returnToService() {
		
	}
	
	/**
	 * If the room is in service
	 * @return in service
	 */
	public boolean isInService() {
		return false;
	}
	
	/**
	 * take the room out of service
	 */
	public void takeOutOfService() {
		
	}
	
	/**
	 * Reserves the room
	 * @param client the client to lease to
	 * @param date the date to lease to
	 * @param i the first number
	 * @param j the second number
	 * @throws RentalCapacityException if the capacity is too high
	 * @throws RentalDateException if the date is wrong
	 * @throws RentalOutOfServiceException if the rental is out of service
	 * @return a lease
	 */
	public abstract Lease reserve(Client client, LocalDate date, int i, int j) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException; 
	
	/**
	 * Records the previous lease for the room
	 * @param i the first number
	 * @param client the client to lease to
	 * @param endDate the date to start
 	 * @param startDate the date to lease to
	 * @param k the second number
	 * @throws RentalCapacityException if the capacity is too high
	 * @throws RentalDateException if the date is wrong
	 * @return a lease
	 */
	public abstract Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int k) throws RentalDateException, RentalCapacityException;
	
	/**
	 * Checks the dates
	 * @param startDate the date to start
	 * @param endDate the date to end
	 * @throws RentalDateException if the date is wrong
	 */
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		
	}
	
	/**
	 * Checks the lease condition
	 * @param client the client
	 * @param date the date
	 * @param i the first number
	 * @param j the second number
	 */
	protected void checkLeaseConditions(Client client, LocalDate date, int i, int j) throws RentalOutOfServiceException {
		
	}
	
	/**
	 * Removes from service
	 * @param date start date
	 * @return the lease
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		return null;
	}
	
	/**
	 * The cut off
	 * @param date cut off date
	 * @return the index
	 */
	protected int cutoffIndex(LocalDate date) {
		return 0;
	}
	
	/**
	 * Cancel the lease 
	 * @param i number to cancel for
	 * @return the lease
	 */
	public Lease cancelLeaseByNumber(int i) {
		return null;
	}
	
	/**
	 * adds a lease
	 * @param lease to add
	 */
	public void addLease(Lease lease) {
		
	}
	
	/**
	 * lists the leases
	 * @return the leases
	 */
	public String[] listLeases() {
		return null;
	}
	
	/**
	 * Gets the description
	 * @return the description
	 */
	public String getDescription() {
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
	 * equals
	 * @param o the object to chekc
	 * @return if equals
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
}
