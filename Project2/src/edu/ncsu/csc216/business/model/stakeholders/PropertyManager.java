/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * Manages the properties
 * @author Alex Raum, Walker Clem
 *
 */
public class PropertyManager implements Landlord {

	/** the earliest date */
	public static final LocalDate EARLIEST_DATE = LocalDate.of(2020, 1, 1); // need to double check this value with TA
	/** the latest date */
	public static final LocalDate LATEST_DATE = LocalDate.of(2029, 12, 31); // need to double check this value with TA
	/** the kind of filter  */
	private String kindFilter;
	/** filter if in service */
	private boolean inServiceFilter;
	/** instance of property manager */
	private static PropertyManager instance = new PropertyManager();
	/** list of customers */
	private SimpleArrayList<Client> customerBase;
	/** list of rooms */
	private SortedLinkedListWithIterator<RentalUnit> rooms;
	
	/**
	 * Gets an instance of property manager
	 * @return the instance
	 */
	public static PropertyManager getInstance() {
		return instance;
		// TODO if there is no instance, this method calls the private constructor, 
		// which simply initializes its customerBase and rooms to empty lists.
	}
	
	/**
	 * Adds a new client with the given name and id to the client database. 
	 *  
	 * @param name Name of the new client
	 * @param id Unique id of the new client 
	 * @return The new Client who was created and registered
	 * @throws DuplicateClientException if id of the new client matches one for an existing client.
	 * @throws IllegalArgumentException if the name is null, empty (when trimmed), or contains
	 *         any characters that are not blanks or not alphanumeric. Also throws
	 *         IllegalArgumentException if the id is null, empty (when trimmed), or 
	 *         contains any characters that are non-alphanumeric or that don't belong to the 
	 *         set ['@', '#', '$'].
	 */
	@Override
	public Client addNewClient(String name, String id) throws DuplicateClientException {
		Client client = new Client(name, id);
		if (customerBase.contains(client)) {
			throw new DuplicateClientException("Client with this ID already exists");
		}
		customerBase.add(client);
		return client;
	}
	
	/**
	 * Adds a new RentalUnit with the given parameters to the system.
	 * 
	 * @param kind Type of RentalUnit (starts with 'O' for office, 'C' for conference room,
	 *        'H' for hotel suite)
	 * @param location String of the form FF-RR, where FF is the floor, and RR is the room.
	 * @param capacity Number of people the unit can accommodate on any single day
	 * @return The new RentalUnit that was created
	 * @throws IllegalArgumentException if the parameters do not describe a valid location  
	 *         and type
	 * @throws DuplicateRoomException if the floor and room match another rental unit already
	 *         in the Landlord's property database
	 */
	@Override
	public RentalUnit addNewUnit(String kind, String location, int capacity) throws DuplicateRoomException {
		RentalUnit unit;
		if (kind == "C") {
			unit = new ConferenceRoom(location, capacity);
		} else if (kind == "H") {
			unit = new HotelSuite(location, capacity);
		} else if (kind == "O") {
			unit = new Office(location, capacity);
		} else {
			throw new IllegalArgumentException("Invalid Rental Unit type");
		}
		
		try {
			rooms.add(unit);
			return unit;
		} catch (IllegalArgumentException e) {
			throw new DuplicateRoomException("Rental Unit at this location already exists");
		}
//		if (rooms.contains(unit)) {
//			throw new DuplicateRoomException("Rental Unit at this location already exists");
//		}
//		rooms.add();
//		return null;
	}
	
	/**
	 * Adds a lease
	 * @param client the client
	 * @param i first number
	 * @param unit the unit
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param j the second number
	 */
	public void addLeaseFromFile(Client client, int i, RentalUnit unit, LocalDate startDate, LocalDate endDate, int j) {
		
	}
	
	/**
	 * Cancel the lease
	 */
	@Override
	public void cancelClientsLease(int clientIndex, int leaseIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Return the room to service
	 */
	@Override
	public void returnToService(int propertyIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Remove the room from service
	 */
	@Override
	public RentalUnit removeFromService(int propertyIndex, LocalDate start) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Close the rental 
	 */
	@Override
	public void closeRentalUnit(int propertyIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Create a new lease
	 */
	@Override
	public Lease createLease(int clientIndex, int propertyIndex, LocalDate start, int duration, int people) {
		return null;
	}
	
	/**
	 * List the clients
	 */
	@Override
	public String[] listClients() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * List the leases for client
	 */
	@Override
	public String[] listClientLeases(int clientIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * list the rental units 
	 */
	@Override
	public String[] listRentalUnits() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * List the leases for rental units
	 */
	@Override
	public String[] listLeasesForRentalUnit(int propertyIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the unit at the location
	 * @param location the location
	 * @return the unit
	 */
	public RentalUnit getUnitAtLocation(String location) {
		return null;
	}
	
	/**
	 * Filters the units
	 */
	@Override
	public void filterRentalUnits(String filter1, boolean filter2) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Clears the data of units and clients
	 */
	@Override
	public void flushAllData() {
		// TODO Auto-generated method stub

	}
}
