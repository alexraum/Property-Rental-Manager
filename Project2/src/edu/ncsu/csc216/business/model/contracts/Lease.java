/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Creates the Lease object
 * 
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
	 * 
	 * @param client the client for the least
	 * @param unit the unit for the lease
	 * @param startDate start of the lease
	 * @param endDate end of the lease
	 * @param the number of occupants for the lease
	 */
	public Lease(Client owner, RentalUnit property, LocalDate startDate, LocalDate endDate, int numOccupants) {
		this(confirmationCounter++, owner, property, startDate, endDate, numOccupants);
	}
	
	/**
	 * The Lease constructor primary constructor, contains
	 * confirmationNumber parameter.
	 * 
	 * @param the confirmation number of  the Lease
	 * @param client the client for the Lease
	 * @param unit the unit for the Lease
	 * @param startDate start of the Lease
	 * @param endDate end of the Lease
	 * @param the number of occupants for the Lease
	 */
	public Lease(int confirmationNumber, Client owner, RentalUnit property, LocalDate startDate, LocalDate endDate, int numOccupants) {
		if (confirmationCounter > MAX_CONF_NUM) {
			confirmationCounter = 0;
			confirmationNumber = 0;
		}
		if (confirmationNumber > confirmationCounter) {
			confirmationCounter = confirmationNumber + 1;
		}
		this.confirmationNumber = confirmationNumber;
		this.owner = owner;
		this.property = property;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOccupants = numOccupants;
	}
	
	/**
	 * Returns the confirmation number of the Lease
	 * 
	 * @return confirmation number
	 */
	public int getConfirmationNumber() {
		return this.confirmationNumber;
	}
	
	/**
	 * Returns the client of the Lease
	 * 
	 * @return the client
	 */
	public Client getClient() {
		return this.owner;
	}
	
	/**
	 * Returns the property of the Lease
	 * 
	 * @return the property 
	 */
	public RentalUnit getProperty() {
		return this.property;
	}
	
	/**
	 * Returns the start date of the Lease
	 * 
	 * @return the start date
	 */
	public LocalDate getStart() {
		return this.startDate;
	}
	
	/**
	 * Returns the end date of the Lease
	 * 
	 * @return the end date
	 */
	public LocalDate getEnd() {
		return this.endDate;
	}
	
	/**
	 * Returns the number of occupants for the Lease
	 * 
	 * @return the number of occupants
	 */
	public int getNumOccupants() {
		return this.numOccupants;
	}
	
	/**
	 * Sets the end date earlier for the Lease
	 * 
	 * @param date the new date
	 */
	public void setEndDateEarlier(LocalDate date) {
		if (date.isAfter(endDate) || date.isBefore(startDate)) {
			throw new IllegalArgumentException();
		}
		this.endDate = date;
		
		
		
//		if (getProperty() instanceof HotelSuite && date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
//			this.endDate = date;
//		} else if (getProperty() instanceof HotelSuite && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
//			while (!date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
//				date = date.minusDays(1);
//			}
//			this.endDate = date;
//		}
////		if (either of these lease modifications would force the lease to cover only one day rather than at least one week) {
////			the lease is cancelled
////		}
//		if (getProperty() instanceof ConferenceRoom) {
//			this.endDate = date.minusDays(1);
//		}
//		if (getProperty() instanceof Office) {
////			end date is set to the last day of the month immediately before the cutoff date
//			Month m = date.getMonth();
//			while (date.getMonth().equals(m)) {
//				date = date.minusDays(1);
//			}
//			this.endDate = date;
//		}
////		if (this.endDate is now before this.startDate) {
////			the lease is cancelled
////		}
	}
	
	/**
	 * Returns the Lease data in an array of Strings
	 * 
	 * @return the Lease data
	 */
	public String[] leaseData() {
		String[] leaseData = new String[6];
		leaseData[0] = String.format("%06d", this.confirmationNumber) + "";
		leaseData[1] = this.startDate + " to " + endDate;
		leaseData[2] = this.numOccupants + "";
		int num = this.property.getDescription().indexOf("|");
		leaseData[3] = this.property.getDescription().substring(0, num - 1);
		leaseData[4] = this.owner.getName();
		leaseData[5] = this.owner.getId();
		return leaseData;
	}
	
	/**
	 * Resets the confirmation counter to the given parameter
	 * 
	 * @param newCounter the value to reset the counter to
	 */
	public static void resetConfirmationNumbering(int newCounter) {
		if (newCounter < 0 || newCounter > MAX_CONF_NUM) {
			throw new IllegalArgumentException();
		}
		confirmationCounter = newCounter;
	}
	
	/**
	 * Compares Leases
	 * 
	 * @param lease the Lease to compare to 
	 * @return which Lease is first
	 */
	public int compareTo(Lease lease) {
		if (!startDate.equals(lease.startDate)) {
			return startDate.compareTo(lease.startDate);
		} else {
			return confirmationNumber - lease.confirmationNumber;
		}
//		int thisConfNum;
//		int confNum;
//		if (this.startDate.compareTo(lease.getStart()) < 0) {
//			return 1;
//		} else if (this.startDate.compareTo(lease.getStart()) > 0) {
//			return -1;
//		} else {
//			thisConfNum = getConfirmationNumber();
//			confNum = lease.getConfirmationNumber();
//		}
//		if (thisConfNum > confNum) {
//			return 1;
//		} else if (thisConfNum < confNum) {
//			return -1;
//		} else {
//			return 0;
//		}
	}
}
