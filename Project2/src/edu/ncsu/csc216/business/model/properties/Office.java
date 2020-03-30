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
public class Office extends RentalUnit {

	/** */
	public static final int MAX_CAPACITY = 150;
	/** */
	private static final int CAL_ROWS = 0; // need to double check this value with TA
	/** */
	private static final int CAL_COLS = 0; // need to double check this value with TA
	/** */
	private int[][] calendar;
	
	/**
	 * 
	 * @param s
	 * @param i
	 */
	public Office(String s, int i) throws RentalCapacityException, RentalDateException {
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
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int k) {
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	protected int remainingCapacityFor(LocalDate date) {
		return 0;
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
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	protected static int getMonthsDuration(LocalDate startDate, LocalDate endDate) {
		return 0;
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
	public void checkDates(LocalDate startDate, LocalDate endDate) {
		
	}
}
