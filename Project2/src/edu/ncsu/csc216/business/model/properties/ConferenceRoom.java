/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The ConferenceRoom class provides all necessary state and behavior 
 * for a conference room that can be rented by a Client, it is a child
 * class of RentalUnit.
 * 
 * @author Alex Raum, Walker Clem
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
	
	/**
	 * The reserve method is used to reserve the rental unit for a new Lease
	 * 
	 * @param client the client reserving the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease
	 * @param duration the duration of the lease
	 * @param occupants the number of occupants for the lease
	 * 
	 * @return the Lease reserving the rental unit
	 * 
	 * @throws RentalCapacityException if the rental unit cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 * @throws RentalOutOfServiceException if the rental unit is currently out of service
	 */
	@Override
	public Lease reserve(Client client, LocalDate startDate, int duration, 
			int occupants) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		LocalDate endDate = startDate.plusDays(duration - 1);
		checkLeaseConditions(client, startDate, duration, occupants);
		if (!(startDate instanceof LocalDate) || !(endDate instanceof LocalDate) || duration > MAX_DURATION) {
			throw new RentalDateException("Invalid date");
		}
		if (occupants > this.getCapacity()) {
			throw new RentalCapacityException("Too many occupants");
		}
		for (int i = 0; i < myLeases.size(); i++) {
			if (startDate.isBefore(myLeases.get(i).getEnd()) && endDate.isAfter(myLeases.get(i).getStart())) {
				throw new RentalDateException("Invalid date");
			}
//			if (endDate.isAfter(myLeases.get(i).getStart()) && endDate.isBefore(myLeases.get(i).getEnd())) {
//				throw new RentalDateException("Invalid date");
//			}
		}
		this.checkDates(startDate, endDate);
		return new Lease(0, client, this, startDate, endDate, occupants);
	}
	
	/**
	 * A method for reserving the rental unit of an existing lease
	 * 
	 * @param confirmationNumber the confirmation number of the lease
	 * @param client the client of the lease
	 * @param startDate the start date of the lease
	 * @param endDate the end date of the lease 
	 * @param numOccupants the number of occupants of the lease 
	 * 
	 * @return the Lease that the rental unit is being reserved for
	 * 
	 * @throws RentalCapacityException if the rental unit cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 */
	@Override
	public Lease recordExistingLease(int confirmationNumber, Client client, LocalDate startDate,
			LocalDate endDate, int numOccupants) throws RentalCapacityException, RentalDateException {
		Lease lease = new Lease(confirmationNumber, client, this, startDate, endDate, numOccupants);
		return lease;
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
				l.setEndDateEarlier(date.minusDays(1));
			}
		}
		return list;
	}
	
	/**
	 * Returns a String description of this conference rooms
	 * 
	 * @return a description of this conference room as a String
	 */
	@Override
	public String getDescription() {
		return "Conference Room: " + super.getDescription();
	}
}
