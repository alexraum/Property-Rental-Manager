/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import java.time.LocalDate;

import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Creates the least object
 * @author Alex Raum, Walker Clem
 *
 */
public class Lease implements Comparable<Lease> {
	
	/** the confirmation counter for the lease */
	private static int confirmationCounter;
	/** the max confirmation number */
	private static final int MAX_CONF_NUM = 999999;
	/** the confirmation number for the lease */
	private int confirmationNumber;
	/** the start date of the lease */
	private LocalDate startDate;
	/** the end date of the lease */
	private LocalDate endDate;
	/** the number of occupants under the lease */
	private int numOccupants;
	/** the owner of the lease */
	private Client owner;
	/** the property for the lease */
	private RentalUnit property;
	
	/**
	 * The Lease constructor
	 * @param client the client for the least
	 * @param unit the unit for the lease
	 * @param startDate start of the lease
	 * @param endDate end of the lease
	 * @param i the number of lease
	 */
	public Lease(Client client, RentalUnit unit, LocalDate startDate, LocalDate endDate, int i) {
		
	}
	
	/**
	 * The lease constructor with extra number
	 * @param i the number of lease
	 * @param client the client for the least
	 * @param unit the unit for the lease
	 * @param startDate start of the lease
	 * @param endDate end of the lease
	 * @param j the number of lease
	 */
	public Lease(int i, Client client, RentalUnit unit, LocalDate startDate, LocalDate endDate, int j) {
		
	}
	
	/**
	 * Returns the confirmation number of the lease
	 * @return confirmation number
	 */
	public int getConfirmationNumber() {
		return 0;
	}
	
	/**
	 * Returns the client of the lease
	 * @return the client
	 */
	public Client getClient() {
		return null;
	}
	
	/**
	 * Returns the property of the lease
	 * @return the property 
	 */
	public RentalUnit getProperty() {
		return null;
	}
	
	/**
	 * Returns the start date of the lease
	 * @return the start date
	 */
	public LocalDate getStart() {
		return null;
	}
	
	/**
	 * Returns the end date of the lease
	 * @return the end date
	 */
	public LocalDate getEnd() {
		return null;
	}
	
	/**
	 * Returns the number of occupants for the lease
	 * @return the number of occupants
	 */
	public int getNumOccupants() {
		return 0;
	}
	
	/**
	 * Sets the end date earlier for the lease
	 * @param date the new date
	 */
	public void setEndDateEarlier(LocalDate date) {
		
	}
	
	/**
	 * Returns the lease data in an array of strings
	 * @return the lease data
	 */
	public String[] leaseData() {
		return null;
	}
	
	/**
	 * Resets the confirmation number
	 * @param number the lease to reset
	 */
	public static void resetConfirmationNumbering(int number) {
		
	}
	
	/**
	 * Compares leases
	 * @param l the lease to compare to 
	 * @return which lease is first
	 */
	public int compareTo(Lease l) {
		return 0;
	}
}
