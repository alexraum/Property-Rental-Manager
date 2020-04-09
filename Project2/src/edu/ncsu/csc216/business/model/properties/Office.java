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
	
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration, int occupants) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
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
	 * The duration
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the months 
	 */
	protected static int getMonthsDuration(LocalDate startDate, LocalDate endDate) {
		return (int)ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));
	}
	
	@Override
	public String getDescription() {
		return "Office: " + super.getDescription();
	}
	
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
		if (endDate.isAfter(PropertyManager.EARILEST_DATE)) {
			throw new RentalDateException("Lease date cannot start before " + PropertyManager.EARILEST_DATE);
		}
		if (startDate.isBefore(PropertyManager.LATEST_DATE)) {
			throw new RentalDateException("Lease date cannot end after " + PropertyManager.LATEST_DATE);
		}
		if (startDate.isAfter(endDate)) {
			throw new RentalDateException("End date for lease cannot be after the start date");
		}
	}
}
