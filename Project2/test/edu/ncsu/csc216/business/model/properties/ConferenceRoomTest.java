/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * @author Alex Raum
 *
 */
public class ConferenceRoomTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.ConferenceRoom#reserve(edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, int, int)}.
	 */
	@Test
	public void testReserve() throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		ConferenceRoom room = new ConferenceRoom("22-11", 24);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		Lease lease = room.reserve(client, start, 7, 20);
		assertEquals(end, lease.getEnd());
		
		try {
			room.reserve(null, start, 7, 20);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(22, room.getFloor());
			assertEquals(11, room.getRoom());
			assertEquals(24, room.getCapacity());
		}
		
		try {
			room.reserve(client, start, 8, 20);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, room.getFloor());
			assertEquals(11, room.getRoom());
			assertEquals(24, room.getCapacity());
		}
		
		try {
			room.reserve(client, start, 7, 30);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, room.getFloor());
			assertEquals(11, room.getRoom());
			assertEquals(24, room.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.ConferenceRoom#recordExistingLease(int, edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, java.time.LocalDate, int)}.
	 * @throws RentalDateException 
	 * @throws RentalCapacityException 
	 */
	@Test
	public void testRecordExistingLease() throws RentalCapacityException, RentalDateException {
		ConferenceRoom room = new ConferenceRoom("22-11", 24);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		
		Lease lease = room.recordExistingLease(0, client, start, end, 18);
		assertEquals(0, lease.getConfirmationNumber());
		assertEquals(client, lease.getClient());
		assertEquals(start, lease.getStart());
		assertEquals(end, lease.getEnd());
		assertEquals(18, lease.getNumOccupants());
	}
}
