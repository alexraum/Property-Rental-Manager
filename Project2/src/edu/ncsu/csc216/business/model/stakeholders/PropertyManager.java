/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * @author Alex Raum
 *
 */
public class PropertyManager implements Landlord {

	/** */
	public static final LocalDate EARILEST_DATE = null; // need to double check this value with TA
	/** */
	public static final LocalDate LATEST_DATE = null; // need to double check this value with TA
	/** */
	private String kindFilter;
	/** */
	private boolean inServiceFilter;
	/** */
	private PropertyManager instance;
	/** */
	private SimpleArrayList customerBase;
	/** */
	private SortedLinkedListWithIterator rooms;
	
	/**
	 * 
	 * @return
	 */
	public static PropertyManager getInstance() {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public Client addNewClient(String name, String id) throws DuplicateClientException {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public RentalUnit addNewUnit(String kind, String location, int capacity) throws DuplicateRoomException {
		return null;
	}
	
	/**
	 * 
	 * @param client
	 * @param i
	 * @param unit
	 * @param startDate
	 * @param endDate
	 * @param j
	 */
	public void addLeaseFromFile(Client client, int i, RentalUnit unit, LocalDate startDate, LocalDate endDate, int j) {
		
	}
	
	/**
	 * 
	 */
	@Override
	public void cancelClientsLease(int clientIndex, int leaseIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	@Override
	public void returnToService(int propertyIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	@Override
	public RentalUnit removeFromService(int propertyIndex, LocalDate start) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void closeRentalUnit(int propertyIndex) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	@Override
	public Lease createLease(int clientIndex, int propertyIndex, LocalDate start, int duration, int people) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String[] listClients() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String[] listClientLeases(int clientIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String[] listRentalUnits() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public String[] listLeasesForRentalUnit(int propertyIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	public RentalUnit getUnitAtLocation(String location) {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void filterRentalUnits(String filter1, boolean filter2) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	@Override
	public void flushAllData() {
		// TODO Auto-generated method stub

	}
}
