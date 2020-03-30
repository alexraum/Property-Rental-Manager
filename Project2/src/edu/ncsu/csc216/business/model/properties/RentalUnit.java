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
 * @author Alex Raum
 *
 */
public abstract class RentalUnit {

	/** */
	public static final int MAX_FLOOR = 45;
	/** */
	public static final int MIN_FLOOR = 1;
	/** */
	public static final int MAX_ROOM = 99;
	/** */
	public static final int MIN_ROOM = 10;
	/** */
	private boolean inService;
	/** */
	private int floor;
	/** */
	private int room;
	/** */
	private int capacity;
	/** */
	protected SortedLinkedListWithIterator myLeases;
	
	/**
	 * 
	 * @param s
	 * @param i
	 */
	public RentalUnit(String s, int i) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCapacity() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFloor() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRoom() {
		return 0;
	}
	
	/**
	 * 
	 * @param unit
	 * @return
	 */
	public int compareTo(RentalUnit unit) {
		return 0;
	}
	
	/**
	 * 
	 */
	public void returnToService() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isInService() {
		return false;
	}
	
	/**
	 * 
	 */
	public void takeOutOfService() {
		
	}
	
	/**
	 * 
	 * @param client
	 * @param date
	 * @param i
	 * @param j
	 * @return
	 */
	public abstract Lease reserve(Client client, LocalDate date, int i, int j) throws RentalCapacityException, RentalDateException;
	
	/**
	 * 
	 * @param i
	 * @param client
	 * @param startDate
	 * @param endDate
	 * @param k
	 * @return
	 */
	public abstract Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int k);
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public void checkDates(LocalDate startDate, LocalDate endDate) {
		
	}
	
	/**
	 * 
	 * @param client
	 * @param date
	 * @param i
	 * @param j
	 */
	protected void checkLeaseConditions(Client client, LocalDate date, int i, int j) {
		
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	protected int cutoffIndex(LocalDate date) {
		return 0;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Lease cancelLeaseByNumber(int i) {
		return null;
	}
	
	/**
	 * 
	 * @param lease
	 */
	public void addLease(Lease lease) {
		
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
	 * @return
	 */
	public String getDescription() {
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
}
