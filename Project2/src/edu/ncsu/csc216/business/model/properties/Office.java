/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

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
	/** the number of rows in the capacity calendar */
	private static final int CAL_ROWS = 10;
	/** the number of columns in the capacity calendar */
	private static final int CAL_COLS = 12;
	/** calendar that tracks an Offices capacity */
	private int[][] calendar;
	/** constant used to shift the start year to 0 */
	private static final int YEAR_SHIFT = 2020;
	/** constant used to shift the start month to 0 */
	private static final int MONTH_SHIFT = 1;
	/** constants that represents the number of months per year */
	private static final int MONTHS_PER_YEAR = 12;
	
	/**
	 * Constructor for the Office class
	 * 
	 * @param location the location of the Office
	 * @param capacity the capacity of the Office
	 * @throws IllegalArgumentException if the capacity
	 *         parameter is greater than MAX_CAPACITY
	 */
	public Office(String location, int capacity) {
		super(location, capacity);
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
		calendar = new int[CAL_ROWS][CAL_COLS];
		for (int[] row : calendar) {
			Arrays.fill(row, capacity);
		}
	}
	
	/**
	 * The reserve method is used to reserve the office for a new Lease.
	 * 
	 * @param client the client creating the lease
	 * @param startDate the start date of the lease
	 * @param duration the duration of the lease
	 * @param occupants the number of occupants for the lease
	 * @return the Lease for the Office
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 *         occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 * @throws RentalOutOfServiceException if the office is currently out of service
	 * @throws IllegalArgumentException if any of the parameters are invalid
	 */
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration,
			int occupants) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		LocalDate endDate = startDate.plusMonths(duration).minusDays(1);
		if (client == null || startDate == null || duration < 1 || occupants < 1) {
			throw new IllegalArgumentException();
		}
		if (!this.isInService()) {
			throw new RentalOutOfServiceException("Not in service");
		}
		if (occupants > super.getCapacity()) {
			throw new RentalCapacityException("Too many occupants");
		}
		this.checkDates(startDate, endDate);
		int startMonth = startDate.getMonthValue() - MONTH_SHIFT;
		int startYear = startDate.getYear() - YEAR_SHIFT;
		int endMonth = endDate.getMonthValue() - MONTH_SHIFT;
		int endYear = endDate.getYear() - YEAR_SHIFT;
		for (int i = startYear; i <= endYear; i++) {
			for (int j = startMonth; j <= endMonth; j++) {
				if (calendar[i][j] < occupants) {
					throw new RentalCapacityException("Capacity is breached");
				}
			}
		}
		Lease lease = new Lease(client, this, startDate, endDate, occupants);
		this.addLease(lease);
		return lease;
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
	 *         occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date is not the first day of the
	 *         month and the end date is not the last day of the month
	 */
	@Override
	public Lease recordExistingLease(int confirmationNumber, Client client, LocalDate startDate, 
			LocalDate endDate, int numOccupants) throws RentalCapacityException, RentalDateException {
		if (numOccupants > this.getCapacity()) {
			throw new RentalCapacityException("Too many occupants");
		}
		int startMonth = startDate.getMonthValue() - MONTH_SHIFT;
		int startYear = startDate.getYear() - YEAR_SHIFT;
		int endMonth = endDate.getMonthValue() - MONTH_SHIFT;
		int endYear = endDate.getYear() - YEAR_SHIFT;
		for (int i = startYear; i <= endYear; i++) {
			for (int j = startMonth; j <= endMonth; j++) {
				if (calendar[i][j] < numOccupants) {
					throw new RentalCapacityException("Capacity is breached");
				}
			}
		}
		this.checkDates(startDate, endDate);
		Lease lease = new Lease(confirmationNumber, client, this, startDate, endDate, numOccupants);
		this.addLease(lease);
		return lease;
	}
	
	/**
	 * Protected method that returns the amount of capacity
	 * that is left for this office for the given date.
	 * 
	 * @param date the date to check remaining capacity for
	 * @return the capacity that is left for the given date
	 * @throws IllegalArgumentException if the date is before
	 *         EARLIEST_DATE or after LATEST_DATE
	 */
	protected int remainingCapacityFor(LocalDate date) {
		if (date.isBefore(PropertyManager.EARLIEST_DATE)
				|| date.isAfter(PropertyManager.LATEST_DATE)) {
			throw new IllegalArgumentException();
		}
		int month = date.getMonthValue() - MONTH_SHIFT;
		int year = date.getYear() - YEAR_SHIFT;
		return calendar[year][month];
	}
	
	/**
	 * Calls parent method to obtain Leases then goes through them to 
	 * adjust their end dates as needed.
	 * 
	 * @param date the cutoff date for the leases
	 * @return a list of leases whose end dates have been adjusted 
	 *         according to their rental unit type
	 */
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		SortedList<Lease> list = super.removeFromServiceStarting(date);
		for (int i = 0; i < myLeases.size(); i++) {
			Lease l = myLeases.get(i);
			LocalDate cutoff = date;
			if (l.getEnd().compareTo(cutoff) >= 0) {
				Month m = cutoff.getMonth();
				while (cutoff.getMonth().equals(m)) {
					cutoff = cutoff.minusDays(1);
				}
				if (cutoff.isBefore(l.getStart())) {
					list.add(myLeases.remove(i));
				} else {
					l.setEndDateEarlier(cutoff);
				}
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
	 *         the end date
	 */
	protected static int getMonthsDuration(LocalDate startDate, LocalDate endDate) {
		// checkDates(startDate, endDate);
		int duration = ((endDate.getYear() - startDate.getYear()) * MONTHS_PER_YEAR)
				+ (endDate.getMonthValue() - startDate.getMonthValue());
		return duration + 1;
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
	 *         of the earliest and latest date range or if the start date
	 *         is earlier than the end date
	 */
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		if (startDate.isBefore(PropertyManager.EARLIEST_DATE)) {
			throw new RentalDateException("Lease date cannot start before " + PropertyManager.EARLIEST_DATE);
		}
		if (endDate.isAfter(PropertyManager.LATEST_DATE)) {
			throw new RentalDateException("Lease date cannot end after " + PropertyManager.LATEST_DATE);
		}
		if (startDate.isAfter(endDate)) {
			throw new RentalDateException("Start date for lease cannot be after the end date");
		}
		if (startDate.getDayOfMonth() != 1 || endDate.getDayOfMonth() != endDate.lengthOfMonth()) {
			throw new RentalDateException("Invalid date");
		}
	}
	
	/**
	 * Adds a Lease to the myLeases field.
	 *
	 * @param lease the Lease to be add
	 * @throws IllegalArgumentException if the lease is for a different
	 *         rental unit
	 */
	@Override
	public void addLease(Lease lease) {
		if (!isInService()) {
			return;
		}
		if (!this.equals(lease.getProperty())) {
			throw new IllegalArgumentException();
		}
		int startMonth = lease.getStart().getMonthValue() - MONTH_SHIFT;
		int startYear = lease.getStart().getYear() - YEAR_SHIFT;
		int endMonth = lease.getEnd().getMonthValue() - MONTH_SHIFT;
		int endYear = lease.getEnd().getYear() - YEAR_SHIFT;
		for (int i = startYear; i <= endYear; i++) {
			for (int j = startMonth; j <= endMonth; j++) {
				calendar[i][j] -= lease.getNumOccupants();
			}
		}
		this.myLeases.add(lease);
	}
	
	/**
	 * Cancel the Lease in the my Leases list with the corresponding 
	 * confirmation number.
	 * 
	 * @param confirmationNumber the confirmation number of the
	 *        Lease to be canceled
	 * @return the Lease that has been canceled
	 * @throws IllegalArgumentException if confirmationNumber parameter
	 *         does not match any leases in the myLeases list 
	 */
	@Override
	public Lease cancelLeaseByNumber(int confirmationNumber) {
		Lease lease = super.cancelLeaseByNumber(confirmationNumber);
		int startMonth = lease.getStart().getMonthValue() - MONTH_SHIFT;
		int startYear = lease.getStart().getYear() - YEAR_SHIFT;
		int endMonth = lease.getEnd().getMonthValue() - MONTH_SHIFT;
		int endYear = lease.getEnd().getYear() - YEAR_SHIFT;
		for (int j = startYear; j <= endYear; j++) {
			for (int k = startMonth; k <= endMonth; k++) {
				calendar[j][k] += lease.getNumOccupants();
			}
		}
		return lease;
	}
}