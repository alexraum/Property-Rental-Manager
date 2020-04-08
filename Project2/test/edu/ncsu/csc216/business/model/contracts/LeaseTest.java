/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * LeaseTest provides methods to check the functionality of the Lease class. 
 *
 * @author Alex Raum
 */
public class LeaseTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#setEndDateEarlier(java.time.LocalDate)}.
	 */
	@Test
	public void testSetEndDateEarlier() {
		Client client = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("3-11", 20);
		HotelSuite suite = new HotelSuite("20-20", 2);
		Office office = new Office("21-21", 100);
		LocalDate roomStart = LocalDate.of(2020, 4, 8);
		LocalDate roomEnd = LocalDate.of(2020, 4, 15);
		LocalDate newRoomEnd = LocalDate.of(2020, 4, 14);
		LocalDate suiteStart = LocalDate.of(2020, 4, 12);
		LocalDate suiteEnd = LocalDate.of(2020, 4, 26);
		LocalDate newSuiteEnd = LocalDate.of(2020, 4, 24);
		LocalDate officeStart = LocalDate.of(2020, 5, 1);
		LocalDate officeEnd = LocalDate.of(2020, 6, 30);
		LocalDate newOfficeEnd = LocalDate.of(2020, 6, 15);
		int numRoomOccupants = 18;
		int numSuiteOccupants = 1;
		int numOfficeOccupants = 75;
		Lease roomLease = new Lease(client, room, roomStart, roomEnd, numRoomOccupants);
		Lease suiteLease = new Lease(client, suite, suiteStart, suiteEnd, numSuiteOccupants);
		Lease officeLease = new Lease(client, office, officeStart, officeEnd, numOfficeOccupants);
		
		roomLease.setEndDateEarlier(newRoomEnd);
		assertEquals(LocalDate.of(2020, 4, 13), roomLease.getEnd());
		
		suiteLease.setEndDateEarlier(newSuiteEnd);
		assertEquals(LocalDate.of(2020, 4, 19), suiteLease.getEnd());
		
		officeLease.setEndDateEarlier(newOfficeEnd);
		assertEquals(LocalDate.of(2020, 5, 31), officeLease.getEnd());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#leaseData()}.
	 */
	@Test
	public void testLeaseData() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#resetConfirmationNumbering(int)}.
	 */
	@Test
	public void testResetConfirmationNumbering() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#compareTo(edu.ncsu.csc216.business.model.contracts.Lease)}.
	 */
	@Test
	public void testCompareTo() {
		//fail("Not yet implemented");
	}

}
