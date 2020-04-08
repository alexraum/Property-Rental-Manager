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
	
	/**
	 * Reserves the conference room
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
	public Lease reserve(Client client, LocalDate date, int i, int j) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		return null;
	}
	
	/**
	 * Records the previous lease for the conference room
	 * @param i the first number
	 * @param client the client to lease to
	 * @param endDate the date to start
 	 * @param startDate the date to lease to
	 * @param j the second number
	 * @throws RentalCapacityException if the capacity is too high
	 * @throws RentalDateException if the date is wrong
	 * @return a lease
	 */
	@Override
	public Lease recordExistingLease(int i, Client client, LocalDate startDate, LocalDate endDate, int j) throws RentalCapacityException, RentalDateException {
		return null;
	}
	
	/**
	 * Removes the service
	 * @param date the date to start
	 * @return the lease info
	 */
	@Override
	public SortedList<Lease> removeFromServiceStarting(LocalDate date) {
		SortedList<Lease> list = super.removeFromServiceStarting(date);
		for (int i = 0; i < myLeases.size(); i++) {
			Lease l = myLeases.get(i);
			
		}
		
		// TODO implement logic here
		
		
		return list;
	}
	
	/**
	 * Gets the description of the room
	 * @param the description of the room
	 */
	@Override
	public String getDescription() {
		return "Conference Room: " + super.getDescription();
	}
}
