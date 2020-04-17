package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The RentalUnitTest class checks the functionality of all methods in the 
 * RentalUnit class to ensure their proper implementation.
 * 
 * @author Walker Clem, Alex Raum
 *
 */
public class RentalUnitTest {

	/**
	 * Checks the proper functionality of the compareTo method
	 */
	@Test
	public void testCompareTo() {
		ConferenceRoom room1 = new ConferenceRoom("23-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("22-11", 20);
		ConferenceRoom room3 = new ConferenceRoom("23-10", 20);
		
		assertTrue(room1.compareTo(room2) > 0);
		assertTrue(room1.compareTo(room3) > 0);
	}

	/**
	 * Checks the proper functionality of the returnToService method
	 */
	@Test
	public void testReturnToService() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		room.takeOutOfService();
		room.returnToService();
		
		assertTrue(room.isInService());
	}

	/**
	 * Checks the proper functionality of the takeOutOfService method
	 */
	@Test
	public void testTakeOutOfService() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		room.takeOutOfService();
		
		assertFalse(room.isInService());
	}

	/**
	 * Checks the proper functionality of the checkDates method
	 */
	@Test
	public void testCheckDates() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		LocalDate startDate = LocalDate.of(2020, 12, 31);
		LocalDate endDate = LocalDate.of(2029, 1, 1);
		LocalDate illegalStartDate = LocalDate.of(2019, 12, 31);
		LocalDate illegalEndDate = LocalDate.of(2030, 1, 1);
		
		try {
			room.checkDates(illegalStartDate, endDate);
			fail();
		} catch (RentalDateException e) {
			assertEquals("Lease date cannot start before 2020-01-01", e.getMessage());
		}
		
		try {
			room.checkDates(startDate, illegalEndDate);
			fail();
		} catch (RentalDateException e) {
			assertEquals("Lease date cannot end after 2029-12-31", e.getMessage());
		}
		
		try {
			room.checkDates(endDate, startDate);
			fail();
		} catch (RentalDateException e) {
			assertEquals("Start date for lease cannot be after the end date", e.getMessage());
		}
	}

	/**
	 * Checks the proper functionality of the checkLeaseConditions method
	 * @throws RentalOutOfServiceException if there is a room out of service
	 */
	@Test
	public void testCheckLeaseConditions() throws RentalOutOfServiceException {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		Client client = new Client("Alex Raum", "maraum");
		
		try {
		    room.checkLeaseConditions(null, LocalDate.of(2020, 12, 31), 5, 20);	
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(20, room.getCapacity());
		}
		
		room.takeOutOfService();
		
		try {
		    room.checkLeaseConditions(client, LocalDate.of(2020, 12, 31), 5, 20);	
		    fail();
		} catch (RentalOutOfServiceException e) {
			assertEquals(20, room.getCapacity());
		}
	}

	/**
	 * Checks the proper functionality of the removeFromServiceStarting method
	 */
	@Test
	public void testRemoveFromServiceStarting() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		LocalDate start2 = LocalDate.of(2020, 4, 12);
		LocalDate end2 = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		Lease lease2 = new Lease(12, client2, room, start2, end2, occupants);
		
		room.addLease(lease);
		room.addLease(lease2);
		
		SortedLinkedListWithIterator<Lease> tail = (SortedLinkedListWithIterator<Lease>) room.removeFromServiceStarting(LocalDate.of(2020, 4, 10));
		SortedLinkedListWithIterator<Lease> tail2 = (SortedLinkedListWithIterator<Lease>) room.removeFromServiceStarting(LocalDate.of(2020, 4, 9));
		
		assertFalse(room.isInService());
		assertEquals(1, tail.size());
		assertEquals(0, tail2.size());
	}

	/**
	 * Checks the proper functionality of the cutoffIndex method
	 */
	@Test
	public void testCutoffIndex() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		LocalDate start2 = LocalDate.of(2020, 4, 12);
		LocalDate end2 = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		Lease lease2 = new Lease(12, client2, room, start2, end2, occupants);
		
		room.addLease(lease);
		room.addLease(lease2);
		
		assertEquals(1, room.cutoffIndex(LocalDate.of(2020, 4, 10)));
		assertEquals(-1, room.cutoffIndex(LocalDate.of(2020, 4, 20)));
	}

	/**
	 * Checks the proper functionality of the cancelLeaseByNumber method
	 */
	@Test
	public void testCancelLeaseByNumber() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		LocalDate start2 = LocalDate.of(2020, 4, 12);
		LocalDate end2 = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		Lease lease2 = new Lease(12, client2, room, start2, end2, occupants);
		
		room.addLease(lease);
		room.addLease(lease2);
		
		assertEquals(lease, room.cancelLeaseByNumber(10));
		
		try {
			room.cancelLeaseByNumber(30);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, room.listLeases().length);
		}
	}

	/**
	 * Checks the proper functionality of the addLease method
	 */
	@Test
	public void testAddLease() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		
		room.addLease(lease);
		String[] leases = room.listLeases();
		
		assertEquals(leases[0], "000010 | 2020-04-08 to 2020-04-15 | 18 | Alex Raum (maraum)");
	}

	/**
	 * Checks the proper functionality of the listLeases method
	 */
	@Test
	public void testListLeases() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		
		room.addLease(lease);
		String[] leases = room.listLeases();
		
		assertEquals("000010 | 2020-04-08 to 2020-04-15 | 18 | Alex Raum (maraum)", leases[0]);
	}

	/**
	 * Checks the proper functionality of the getDescription method
	 */
	@Test
	public void testGetDescription() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		assertEquals("Conference Room: 23-11 |  20", room.getDescription());
		
		room.takeOutOfService();
		
		assertEquals("Conference Room: 23-11 |  20 Unavailable", room.getDescription());
	}

	/**
	 * Checks the proper functionality of the equals method
	 */
	@Test
	public void testEqualsObject() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("23-11", 20);
		ConferenceRoom room4 = new ConferenceRoom("22-11", 20);
		ConferenceRoom room5 = new ConferenceRoom("23-12", 20);
		
		assertTrue(room.equals(room2));
		assertFalse(room == null);
		assertFalse(room.equals(room4));
		assertFalse(room.equals(room5));
		
		room2.takeOutOfService();
	}
}
