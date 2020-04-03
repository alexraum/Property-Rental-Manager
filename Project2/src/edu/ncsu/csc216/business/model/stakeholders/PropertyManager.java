/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * Manages the properties
 * @author Alex Raum, Walker Clem
 *
 */
public class PropertyManager implements Landlord {

	/** the earliest date */
	public static final LocalDate EARILEST_DATE = null; // need to double check this value with TA
	/** the latest date */
	public static final LocalDate LATEST_DATE = null; // need to double check this value with TA
	/** the kind of filter  */
	private String kindFilter;
	/** filter if in service */
	private boolean inServiceFilter;
	/** instance of property manager */
	private PropertyManager instance;
	/** list of customers */
	private SimpleArrayList customerBase;
	/** list of rooms */
	private SortedLinkedListWithIterator rooms;
	
	/**
	 * Gets an instance of property manager
	 * @return the instance
	 */
	public static PropertyManager getInstance() {
		return null;
	}
	
	/**
	 * Adds a new client
	 */
	@Override
	public Client addNewClient(String name, String id) throws DuplicateClientException {
		return null;
	}
	
	/**
	 * Adds a new unit
	 */
	@Override
	public RentalUnit addNewUnit(String kind, String location, int capacity) throws DuplicateRoomException {
		return null;
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
