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
public class HotelSuite extends RentalUnit {
	
	/** */
	public static final int MAX_CAPACITY = 2;
	
	/**
	 * 
	 * @param s
	 */
	public HotelSuite(String s) {
		this(s, 0);
	}
	
	/**
	 * 
	 * @param s
	 * @param i
	 */
	public HotelSuite(String s, int i) {
		super(s, i);
	}
	
	/**
	 * 
	 */
	@Override
	public Lease reserve(Client client, LocalDate date, int i, int j) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int j) throws RentalCapacityException, RentalDateException {
		return null;
	}
	
	/**
	 * 
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
	
	/**
	 * 
	 */
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		
	}
}
