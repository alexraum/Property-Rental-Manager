/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The conference room object
 * @author Alex Raum, Walker Clem
 *
 */
public class ConferenceRoom extends RentalUnit {
	
	/** the max capacity of the room */
	public static final int MAX_CAPACITY = 25;
	/** the max duration to rent the room */
	public static final int MAX_DURATION = 7;
	
	/**
	 * Constructor for the ConferenceRoom class
	 * 
	 * @param location the location of the room
	 * @param capacity the capacity of the room
	 */
	public ConferenceRoom(String location, int capacity) {
		super(location, capacity);
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration, int occupants) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		LocalDate endDate = startDate.plusDays(duration);
		if (client == null || startDate == null || duration < 1 || occupants < 1) {
			throw new IllegalArgumentException();
		}
		if (this.isInService()) {
			throw new RentalOutOfServiceException("Not in service");
		}
		if (!(startDate instanceof LocalDate) || !(endDate instanceof LocalDate) || duration > MAX_DURATION) {
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
			if (l.getEnd().compareTo(date) >= 0) {
				l.setEndDateEarlier(date.minusDays(1));
			}
		}
		return list;
	}
	
	@Override
	public String getDescription() {
		return "Conference Room: " + super.getDescription();
	}
}
