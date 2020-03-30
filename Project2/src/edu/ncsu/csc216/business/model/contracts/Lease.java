/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import java.time.LocalDate;

import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * @author Alex Raum
 *
 */
public class Lease implements Comparable<Lease> {
	
	/** */
	private static int confirmationCounter;
	/** */
	private static final int MAX_CONF_NUM = 999999;
	/** */
	private int confirmationNumber;
	/** */
	private LocalDate startDate;
	/** */
	private LocalDate endDate;
	/** */
	private int numOccupants;
	/** */
	private Client owner;
	/** */
	private RentalUnit property;
	
	/**
	 * 
	 * @param client
	 * @param unit
	 * @param startDate
	 * @param endDate
	 * @param i
	 */
	public Lease(Client client, RentalUnit unit, LocalDate startDate, LocalDate endDate, int i) {
		
	}
	
	/**
	 * 
	 * @param i
	 * @param client
	 * @param unit
	 * @param startDate
	 * @param endDate
	 * @param j
	 */
	public Lease(int i, Client client, RentalUnit unit, LocalDate startDate, LocalDate endDate, int j) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getConfirmationNumber() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public Client getClient() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public RentalUnit getProperty() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate getStart() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate getEnd() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumOccupants() {
		return 0;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void setEndDateEarlier(LocalDate date) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] leaseData() {
		return null;
	}
	
	/**
	 * 
	 * @param number
	 */
	public static void resetConfirmationNumbering(int number) {
		
	}
	
	/**
	 * 
	 * @param l
	 * @return
	 */
	public int compareTo(Lease l) {
		return 0;
	}
}
