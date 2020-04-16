/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;
import java.util.Scanner;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalCapacityException;
import edu.ncsu.csc216.business.model.properties.RentalDateException;
import edu.ncsu.csc216.business.model.properties.RentalOutOfServiceException;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * The PropertyManager class represents the single property
 * manager for the entire system. PropertyManager implements 
 * the Singleton and Factory design patterns.
 * 
 * @author Alex Raum, Walker Clem
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
	private static PropertyManager instance;
	/** list of customers */
	private SimpleArrayList<Client> customerBase;
	/** list of rooms */
	private SortedLinkedListWithIterator<RentalUnit> rooms;
	
	/**
	 * The private constructor the the PropertyManager class,
	 * it initializes the two fields to be empty lists.
	 */
	private PropertyManager() {
		this.customerBase = new SimpleArrayList<Client>();
		this.rooms = new SortedLinkedListWithIterator<RentalUnit>();
	}
	
	/**
	 * Gets an instance of property manager
	 * 
	 * @return the instance
	 */
	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		} 
		return instance;
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
	}
	
	/**
	 * Adds a lease
	 * 
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
	 * Cancels the lease in the given position on the client's list of leases.
	 * 
	 * @param clientIndex  Index of the client whose lease is to be cancelled
	 * @param leaseIndex  Position of the lease in the client's list
	 * @throws IllegalArgumentException if clientIndex or leaseIndex are not valid
	 */
	@Override
	public void cancelClientsLease(int clientIndex, int leaseIndex) {
		if (clientIndex < 0 || clientIndex >= customerBase.size()) {
			throw new IllegalArgumentException();
		}
		if (leaseIndex < 0 || leaseIndex >= customerBase.get(clientIndex).listLeases().length) {
			throw new IllegalArgumentException();
		}
		customerBase.get(clientIndex).cancelLeaseAt(leaseIndex);
	}
	
	/**
	 * Returns the rental unit at the given position to service. Does nothing if the rental 
	 * unit is already in service or if the position does not correspond to any rental unit 
	 * (subject to filtering).
	 * 
	 * @param propertyIndex Position/index of the rental unit (subject to filtering)
	 * @throws IllegalArgumentException if propertyIndex is not a valid index for the 
	 *         rental units currently under consideration
	 */
	@Override
	public void returnToService(int propertyIndex) {
		if (propertyIndex < 0 || propertyIndex >= rooms.size()) {
			throw new IllegalArgumentException();
		}
		rooms.get(propertyIndex).returnToService();
		// TODO Check what is meant by subject to filtering
	}
	
	/**
	 * Cancels all leases for a rental unit on or after a particular date. The remaining 
	 * leases should still be valid.
	 * 
	 * @param propertyIndex  Index for the rental unit (subject to filtering)
	 * @param start Date for starting cancellations
	 * @return the RentalUnit that was removed
	 * @throws IllegalArgumentException if propertyIndex is not a valid index for the 
	 *         rental units currently under consideration
	 */
	@Override
	public RentalUnit removeFromService(int propertyIndex, LocalDate start) {
		if (propertyIndex < 0 || propertyIndex >= rooms.size()) {
			throw new IllegalArgumentException();
		}
		RentalUnit unit = rooms.get(propertyIndex);
		unit.removeFromServiceStarting(start);
		return unit;
	}
	
	/** 
	 * Removes the rental unit at the given index from the Landlord's database and cancels
	 * all leases for that rental unit.
	 * 
	 * @param propertyIndex  Index for the rental unit to be closed (subject to filtering)
	 */
	@Override
	public void closeRentalUnit(int propertyIndex) {
		if (propertyIndex < 0 || propertyIndex >= rooms.size()) {
			throw new IllegalArgumentException();
		}
		rooms.remove(propertyIndex);
		// TODO Still need to cancel all Leases for the removed RentalUnit
	}
	
	/**
	 * Creates a new lease with information based on the given parameters.
	 * 
	 * @param clientIndex Index of the client in the Landlord's customer base
	 * @param propertyIndex Index of the rental unit in the Landlord's filtered list of rental units
	 * @param start Start date for the lease
	 * @param duration Duration of the lease (units depending on rental unit type)
	 * @param people Number of occupants the lease is for
	 * @return the created lease
	 * @throws IllegalArgumentException if the parameters do not constitute valid lease data
	 */
	@Override
	public Lease createLease(int clientIndex, int propertyIndex, LocalDate start, int duration, int people) {
		Client client = customerBase.get(clientIndex);
		RentalUnit unit = rooms.get(propertyIndex);
		try {
			return unit.reserve(client, start, duration, people);
		} catch (RentalOutOfServiceException | RentalDateException | RentalCapacityException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Who are the clients for this Landlord's properties?
	 * 
	 * @return an array of strings, where each string describes a client
	 */
	@Override
	public String[] listClients() {
		String[] clients = new String[customerBase.size()];
		for (int i = 0; i < customerBase.size(); i++) {
			Client client = customerBase.get(i);
			clients[i] = client.getName() + "(" + client.getId() + ")";
		}
		return clients;
	}
	
	/**
	 * What are the leases for a particular client?
	 * 
	 * @param clientIndex Index of the targeted client in the landlord's list of clients
	 * @return an array of strings in which each string describes a lease for the
	 *         targeted client
	 * @throws IllegalArgumentException if the clientIndex does not correspond to any
	 *         client.
	 */
	@Override
	public String[] listClientLeases(int clientIndex) {
		if (clientIndex < 0 || clientIndex >= customerBase.size()) {
			throw new IllegalArgumentException();
		}
		return customerBase.get(clientIndex).listLeases();
	}
	
	/**
	 * What are the rental units for this landlord? (Consider only the units that meet
	 * filters currently in place.)
	 * 
	 * @return an array of strings in which each string describes a rental unit that
	 *         meets all filters in place. There are exactly as many strings in the
	 *         array as there are such rental units.
	 */
	@Override
	public String[] listRentalUnits() {
		int count = 0;
		for (int i = 0; i < rooms.size(); i++) {
			if (inServiceFilter) {
				if (rooms.get(i).getDescription().contains(kindFilter)
						&& rooms.get(i).isInService()) {
					count++;
				}
			} else {
				if (rooms.get(i).getDescription().contains(kindFilter)) {
					count++;
				}
	        }
		}
		String[] rentalArray = new String[count];
		int newCount = 0;
		for (int i = 0; i < rooms.size(); i++) {
			if (inServiceFilter) {
				if (rooms.get(i).getDescription().contains(kindFilter)
						&& rooms.get(i).isInService()) {
					rentalArray[newCount] = rooms.get(i).getDescription();
					newCount++;
				}
			} else {
				if (rooms.get(i).getDescription().contains(kindFilter)) {
					rentalArray[newCount] = rooms.get(i).getDescription();
					newCount++;
				}
			}
		}
		return rentalArray;
	}
	
	/**
	 * What are the leases for the rental unit at this particular index in the filtered
	 * list of rental units?
	 * 
	 * @param propertyIndex Index of the targeted rental unit (subject to filtering)
	 * @return an array of strings in which each string describes a lease for the
	 *         targeted rental unit.
	 * @throw IllegalArgumentException if propertyIndex is not a valid index for the 
	 *         rental units currently under consideration
	 */
	@Override
	public String[] listLeasesForRentalUnit(int propertyIndex) {
		// GOES THROUGH FILTERED LIST
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns the rental unit at given location
	 * 
	 * @param location the location of the unit to be returned
	 * @return the unit with the specified location
	 * @throws IllegalArgumentException if there is no such rental
	 *         unit
	 */
	public RentalUnit getUnitAtLocation(String location) {
		Scanner read = new Scanner(location);
		read.useDelimiter("-");
		int floor = read.nextInt();
		int room = read.nextInt();
		read.close();
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getFloor() == floor && rooms.get(i).getRoom() == room) {
				return rooms.get(i);
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Sets filters for rental units so that only those that match the filters are considered.
	 * 
	 * @param kindFilter String type filter that rental units under consideration must meet
	 * @param inServiceFilter boolean type filter that rental units under consideration must meet
	 */
	public void filterRentalUnits(String kindFilter, boolean inServiceFilter) {
		String kind = kindFilter.trim().toLowerCase();
		char letter = kind.charAt(0);
		if (letter == 'c') {
			kind = "Conference Room";
		} else if (letter == 'h') {
			kind = "Hotel Suite";
		} else if (letter == 'o') {
			kind = "Office";
		} else {
			kind = " ";
		}
		this.kindFilter = kind;
		this.inServiceFilter = inServiceFilter;
	}
	
	/**
	 * Removes all lease, client, and rental unit data from 
	 * the property manager and resets the lease confirmation 
	 * numbering back to 0.
	 */
	@Override
	public void flushAllData() {
		this.customerBase = new SimpleArrayList<Client>();
		this.rooms = new SortedLinkedListWithIterator<RentalUnit>();
		Lease.resetConfirmationNumbering(0);
	}
}
