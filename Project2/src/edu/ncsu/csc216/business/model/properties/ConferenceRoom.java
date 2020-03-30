/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * @author Alex Raum
 *
 */
public class ConferenceRoom extends RentalUnit {
	
	/** */
	public static final int MAX_CAPACITY = 25;
	/** */
	public static final int MAX_DURATION = 7;
	
	/**
	 * 
	 * @param s
	 * @param i
	 */
	public ConferenceRoom(String s, int i) throws RentalCapacityException, RentalDateException {
		super(s, i);
	}
	
	/**
	 * 
	 */
	@Override
	public Lease reserve(Client client, LocalDate date, int i, int j) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int j) {
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String getDescription() {
		return null;
	}
}
