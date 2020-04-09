/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;

/**
 * @author Alex Raum
 *
 */
public class ClientTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.Client#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Alex Raum", "maraum");
		assertTrue(client.equals(client2));
		assertFalse(client.hashCode() == client2.hashCode());
		assertFalse(client.equals(null));
		assertFalse(client.equals(""));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.Client#addNewLease(edu.ncsu.csc216.business.model.contracts.Lease)}.
	 */
	@Test
	public void testAddNewLease() {
		Client client1 = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");

		ConferenceRoom room = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(client1, room, start, end, occupants);
		
		client1.addNewLease(lease);
		String[] leases = client1.listLeases();
		assertEquals("4 | 2020-04-08 to 2020-04-15 | 18 | Conference Room: 14-11 | 20", leases[0]);
		
		try {
			client1.addNewLease(new Lease(client2, room, start, end, occupants));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("4 | 2020-04-08 to 2020-04-15 | 18 | Conference Room: 14-11 | 20", leases[0]);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.Client#listLeases()}.
	 */
	@Test
	public void testListLeases() {
		Client client1 = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(client1, room, start, end, occupants);
		
		client1.addNewLease(lease);
		String[] leases = client1.listLeases();
		assertEquals("3 | 2020-04-08 to 2020-04-15 | 18 | Conference Room: 14-11 | 20", leases[0]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.Client#cancelLeaseAt(int)}.
	 */
	@Test
	public void testCancelLeaseAt() {
		Client client1 = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(client1, room, start, end, occupants);
		
		client1.addNewLease(lease);
		client1.cancelLeaseAt(0);
		
		assertEquals(client1.listLeases().length, 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.Client#cancelLeaseWithNumber(int)}.
	 */
	@Test
	public void testCancelLeaseWithNumber() {
		Client client1 = new Client("Alex Raum", "maraum");
		ConferenceRoom room = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		
		Lease lease = new Lease(client1, room, start, end, occupants);

		client1.addNewLease(lease);
		client1.cancelLeaseWithNumber(6);
		
		assertEquals(client1.listLeases().length, 0);
	}
}
