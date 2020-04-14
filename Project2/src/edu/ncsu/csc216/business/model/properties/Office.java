/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * The Office class provides all necessary state and behavior 
 * for an office object that can be rented by a Client, it is a child
 * class of RentalUnit.
 * 
 * @author Alex Raum, Walker Clem
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
	 * Constructor for the Office class
	 * 
	 * @param location the location of the Office
	 * @param capacity the capacity of the Office
	 */
	public Office(String location, int capacity) {
		super(location, capacity);
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * The reserve method is used to reserve the office for a new Lease
	 * 
	 * @param client the client creating the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease
	 * @param duration the duration of the lease
	 * @param occupants the number of occupants for the lease
	 * @return the Lease for the Office
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 * @throws RentalOutOfServiceException if the office is currently out of service
	 */
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration,
			int occupants) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		LocalDate endDate = startDate.plusWeeks(duration);
		if (client == null || startDate == null || duration < 1 || occupants < 1) {
			throw new IllegalArgumentException();
		}
		if (this.isInService()) {
			throw new RentalOutOfServiceException("Not in service");
		}
		if (!(startDate instanceof LocalDate) || !(endDate instanceof LocalDate) || 
			!(startDate.getDayOfWeek().name().equals("Sunday")) ||
			!(endDate.getDayOfWeek().name().equals("Sunday"))) {
			throw new RentalDateException("Invalid date");
		}
		if (occupants > MAX_CAPACITY) {
			throw new RentalCapacityException("Too many occupants");
		}
		this.checkDates(startDate, endDate);
		return new Lease(0, client, this, startDate, endDate, occupants);
	}
	
	/**
	 * A method for reserving the office for an existing lease
	 * 
	 * @param confirmationNumber the confirmation number of the lease
	 * @param client the client of the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease 
	 * @param numOccupants the number of occupants of the lease 
	 * @return the Lease that the hotel suite is being reserved for
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date is not the first day of the
	 * month and the end date is not the last day of the month
	 */
	@Override
	public Lease recordExistingLease(int confirmationNumber, Client client, LocalDate startDate, 
			LocalDate endDate, int numOccupants) throws RentalCapacityException, RentalDateException {
		return null;
	}
	
	/**
	 * Protected method that returns the amount of capacity
	 * that is left for this office for the given date.
	 * 
	 * @param date the date to check remaining capacity for
	 * @return the capacity that is left for the given date
	 * @throws IllegalArgumentException if the date is < EARLIEST_DATE or
	 * > LATEST_DATE
	 */
	protected int remainingCapacityFor(LocalDate date) {
		return 0;
	}
	
	/**
	 * Calls parent method to obtain Leases then goes through them to 
	 * adjust their end dates as needed.
	 * 
	 * @param date the cutoff date for the leases
	 * @return a list of leases whose end dates have been adjusted 
	 * according to their rental unit type
	 */
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		SortedList<Lease> list = super.removeFromServiceStarting(date);
		for (int i = 0; i < myLeases.size(); i++) {
			Lease l = myLeases.get(i);
			if (l.getEnd().compareTo(date) >= 0) {
				Month m = date.getMonth();
				while (date.getMonth().equals(m)) {
					date = date.minusDays(1);
				}
				l.setEndDateEarlier(date);
			}
			if (l.getEnd().isBefore(l.getStart())) {
				list.add(myLeases.remove(i));
			}
		}
		return list;
	}
	
	/**
	 * Protected method that returns the number of months between
	 * the first parameter and the second.
	 * 
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of months between the start date and
	 * the end date
	 */
	protected static int getMonthsDuration(LocalDate startDate, LocalDate endDate) {
		return (int)ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));
	}
	
	/**
	 * Returns a String description of this hotel suite
	 * 
	 * @return a description of this hotel suite as a String
	 */
	@Override
	public String getDescription() {
		return "Office:          " + super.getDescription();
	}
	
	/**
	 * Checks the start and end dates to ensure that they
	 * are valid and that they don't conflict.
	 * 
	 * @param startDate the start date
	 * @param endDate the end date
	 * @throws RentalDateException if the either date is outside 
	 * of the earliest and latest date range or if the start date
	 * is earlier than the end date
	 */
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		// TODO override, check to make sure the dates for a potential new Lease are valid, such as
		// starting and ending on the correct days of the week or month
		if (endDate.isAfter(PropertyManager.EARLIEST_DATE)) {
			throw new RentalDateException("Lease date cannot start before " + PropertyManager.EARLIEST_DATE);
		}
		if (startDate.isBefore(PropertyManager.LATEST_DATE)) {
			throw new RentalDateException("Lease date cannot end after " + PropertyManager.LATEST_DATE);
		}
		if (startDate.isAfter(endDate)) {
			throw new RentalDateException("End date for lease cannot be after the start date");
		}
	}
}
