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
 * The HotelSuite class provides all necessary state and behavior 
 * for a hotel suite that can be rented by a Client, it is a child
 * class of RentalUnit.
 * 
 * @author Alex Raum, Walker Clem
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
	 * @throws IllegalArgumentException if the capacity 
	 * parameter is greater than the maximum capacity
	 */
	public HotelSuite(String location, int capacity) {
		super(location, capacity);
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * The reserve method is used to reserve the hotel suite for a new Lease
	 * 
	 * @param client the client creating the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease
	 * @param duration the duration of the lease
	 * @param occupants the number of occupants for the lease
	 * @return the Lease for the HotelSuite
	 * @throws RentalCapacityException if the hotel suite cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 * @throws RentalOutOfServiceException if the hotel suite is currently out of service
	 */
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration,
			int occupants) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		LocalDate endDate = startDate.plusWeeks(duration);
		if (client == null || startDate == null || duration < 1 || occupants < 1) {
			throw new IllegalArgumentException();
		}
		if (!this.isInService()) {
			throw new RentalOutOfServiceException("Not in service");
		}
		if (!(startDate instanceof LocalDate) || !(endDate instanceof LocalDate) || 
			!(startDate.getDayOfWeek().name().equals("Sunday")) ||
			!(endDate.getDayOfWeek().name().equals("Sunday"))) {
			throw new RentalDateException("Invalid date");
		}
//		if (the non-Sunday dates in the lease overlap with another lease) {
//			throw new RentalDateException();
//		}
		if (occupants > MAX_CAPACITY) {
			throw new RentalCapacityException("Too many occupants");
		}
		this.checkDates(startDate, endDate);
		Lease lease = new Lease(0, client, this, startDate, endDate, occupants);
		super.addLease(lease);
		return lease;	
	}
	
	/**
	 * A method for reserving the hotel suite for an existing lease
	 * 
	 * @param confirmationNumber the confirmation number of the lease
	 * @param client the client of the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease 
	 * @param numOccupants the number of occupants of the lease 
	 * @return the Lease that the hotel suite is being reserved for
	 * @throws RentalCapacityException if the rental unit cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 */
	@Override
	public Lease recordExistingLease(int confirmationNumber, Client client, LocalDate startDate,
			LocalDate endDate, int numOccupants) throws RentalCapacityException, RentalDateException {
		return null;
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
	
	/**
	 * Returns a String description of this hotel suite
	 * 
	 * @return a description of this hotel suite as a String
	 */
	@Override
	public String getDescription() {
		return "Hotel Suite:     " + super.getDescription();
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
