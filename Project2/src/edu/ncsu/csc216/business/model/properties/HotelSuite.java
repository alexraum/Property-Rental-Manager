/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.DayOfWeek;
import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * The hotel suite object
 * @author Alex Raum, Walker Clem
 *
 */
public class HotelSuite extends RentalUnit {
	
	/** the max capacity of the room */
	public static final int MAX_CAPACITY = 2;
	
	/**
	 * Small Constructor for the HotelSuite class
	 * 
	 * @param location the location of the room
	 */
	public HotelSuite(String location) {
		this(location, 0);
	}
	
	/**
	 * The main Constructor for the HotelSuite class
	 * 
	 * @param location the location of the Suite
	 * @param capacity the capacity of the Suite
	 */
	public HotelSuite(String location, int capacity) {
		super(location, capacity);
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration, int occupants) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
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
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int j) throws RentalCapacityException, RentalDateException {
		return null;
	}
	
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		SortedList<Lease> list = super.removeFromServiceStarting(date);
		for (int i = 0; i < myLeases.size(); i++) {
			Lease l = myLeases.get(i);
			if (l.getEnd().compareTo(date) >= 0 && date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				l.setEndDateEarlier(date);
			}
			if (l.getEnd().compareTo(date) >= 0 && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				while (!date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
					date = date.minusDays(1);
				}
				l.setEndDateEarlier(date);
			}
			// TODO If either of these lease modifications would force the lease to cover only
			// one day rather than at least one week, then the lease is canceled.
		}
		return list;
	}
	
	@Override
	public String getDescription() {
		return "Hotel Suite:     " + super.getDescription();
	}
	
	@Override
	public void checkDates(LocalDate startDate, LocalDate endDate) throws RentalDateException {
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
