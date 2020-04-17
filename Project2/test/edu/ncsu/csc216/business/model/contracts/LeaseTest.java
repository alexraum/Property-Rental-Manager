/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * LeaseTest provides methods to check the functionality of the Lease class. 
 *
 * @author Alex Raum, Walker Clem
 */
public class LeaseTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#setEndDateEarlier(java.time.LocalDate)}.
	 */
	@Test
	public void testSetEndDateEarlier() {
		Client client = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("3-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		LocalDate newEnd = LocalDate.of(2020, 4, 14);
		int occupants = 18;
		Lease lease = new Lease(client, room, start, end, occupants);

		lease.setEndDateEarlier(newEnd);
		assertEquals(LocalDate.of(2020, 4, 14), lease.getEnd());
		
		try {
			lease.setEndDateEarlier(LocalDate.of(2020, 4, 7));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(18, lease.getNumOccupants());
		}
		
		try {
			lease.setEndDateEarlier(LocalDate.of(2020, 4, 16));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(18, lease.getNumOccupants());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#leaseData()}.
	 */
	@Test
	public void testLeaseData() {
		Client client = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("12-11", 20);
		LocalDate roomStart = LocalDate.of(2020, 4, 8);
		LocalDate roomEnd = LocalDate.of(2020, 4, 15);
		int numRoomOccupants = 18;
		
		Lease roomLease = new Lease(client, room, roomStart, roomEnd, numRoomOccupants);
		String[] leaseData = roomLease.leaseData();
		
		assertEquals("000000", leaseData[0]);
		assertEquals("2020-04-08 to 2020-04-15", leaseData[1]);
		assertEquals("18", leaseData[2]);
		assertEquals("Conference Room: 12-11", leaseData[3]);
		assertEquals("Alex Raum", leaseData[4]);
		assertEquals("maraum", leaseData[5]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#resetConfirmationNumbering(int)}.
	 */
	@Test
	public void testResetConfirmationNumbering() {
		Client client = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("12-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
		LocalDate roomStart = LocalDate.of(2020, 4, 8);
		LocalDate roomStart2 = LocalDate.of(2020, 4, 15);
		LocalDate roomEnd = LocalDate.of(2020, 4, 15);
		int numRoomOccupants = 18;
		
		Lease.resetConfirmationNumbering(0);
		Lease lease = new Lease(client, room, roomStart, roomEnd, numRoomOccupants);
		assertEquals(0, lease.getConfirmationNumber());
		Lease lease2 = new Lease(client, room2, roomStart2, roomEnd, numRoomOccupants);
		assertEquals(1, lease2.getConfirmationNumber());
		
		try {
			Lease.resetConfirmationNumbering(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, lease.getConfirmationNumber());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#compareTo(edu.ncsu.csc216.business.model.contracts.Lease)}.
	 */
	@Test
	public void testCompareTo() {
		Client client = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("12-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
		ConferenceRoom room3 = new ConferenceRoom("16-11", 20);
		LocalDate roomStart = LocalDate.of(2020, 4, 8);
		LocalDate roomStart2 = LocalDate.of(2020, 4, 15);
		LocalDate roomEnd = LocalDate.of(2020, 4, 15);
		int numRoomOccupants = 18;
		int numRoomOccupants2 = 19;
		
		Lease lease = new Lease(client, room, roomStart, roomEnd, numRoomOccupants);
		Lease lease2 = new Lease(client, room2, roomStart2, roomEnd, numRoomOccupants);
		Lease lease3 = new Lease(client, room3, roomStart, roomEnd, numRoomOccupants2);
		assertTrue(lease.compareTo(lease2) != 0);
		assertTrue(lease.compareTo(lease3) != 0);
	}
}
