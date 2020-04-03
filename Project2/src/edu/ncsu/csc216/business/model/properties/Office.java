/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The office object
 * @author Alex Raum, Walker Clem
 *
 */
public class Office extends RentalUnit {

	/** the max capacity of the room */
	public static final int MAX_CAPACITY = 150;
	/** the rows */
	private static final int CAL_ROWS = 0; // need to double check this value with TA
	/** the rows */
	private static final int CAL_COLS = 0; // need to double check this value with TA
	/** the calendar */
	private int[][] calendar;
	
	/**
	 * Constructor for the office
	 * @param s the name of the room
	 * @param i the id of the room
	 */
	public Office(String s, int i) {
		super(s, i);
	}
	
	/**
	 * Reserves the office room
	 * @param client the client to lease to
	 * @param date the date to lease to
	 * @param i the first number
	 * @param j the second number
	 * @throws RentalCapacityException if the capacity is too high
	 * @throws RentalDateException if the date is wrong
	 * @throws RentalOutOfServiceException if the rental is out of service
	 * @return a lease
	 */
	@Override
	public Lease reserve(Client client, LocalDate date, int i, int j) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		return null;
	}
	
	/**
	 * Records the previous lease for the office room
	 * @param i the first number
	 * @param client the client to lease to
	 * @param endDate the date to start
 	 * @param startDate the date to lease to
	 * @param k the second number
	 * @throws RentalCapacityException if the capacity is too high
	 * @throws RentalDateException if the date is wrong
	 * @return a lease
	 */
	@Override
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int k) throws RentalCapacityException, RentalDateException {
		return null;
	}
	
	/**
	 * Remaining capacity for the date
	 * @param date the date to check
	 * @return the capacity
	 */
	protected int remainingCapacityFor(LocalDate date) {
		return 0;
	}
	
	/**
	 * Removes the service
	 * @param date the date to start
	 * @return the lease info
	 */
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		return null;
	}
	
	/**
	 * The duration
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the months 
	 */
	protected static int getMonthsDuration(LocalDate startDate, LocalDate endDate) {
		return 0;
	}
	
	/**
	 * Gets the description of the room
	 * @param the description of the room
	 */
	@Override
	public String getDescription() {
		return null;
	}
	
	/**
	 * Checks the dates
	 * @param startDate the date to start
	 * @param endDate the date to end
	 * @throws RentalDateException if the date is wrong
	 */
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		
	}
}
