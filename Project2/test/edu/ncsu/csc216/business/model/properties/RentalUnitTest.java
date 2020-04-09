package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

public class RentalUnitTest {

	@Test
	public void testRentalUnit() {
		try {
			ConferenceRoom room = new ConferenceRoom("22-11", 26);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

	@Test
	public void testGetCapacity() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetFloor() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetRoom() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		ConferenceRoom room1 = new ConferenceRoom("23-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("22-11", 20);
		ConferenceRoom room3 = new ConferenceRoom("23-10", 20);
		
		assertTrue(room1.compareTo(room2) > 0);
		assertTrue(room1.compareTo(room3) > 0);
	}

	@Test
	public void testReturnToService() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		room.takeOutOfService();
		room.returnToService();
		
		assertTrue(room.isInService());
	}

	@Test
	public void testIsInService() {
//		fail("Not yet implemented");
	}

	@Test
	public void testTakeOutOfService() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		room.takeOutOfService();
		
		assertFalse(room.isInService());
	}

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
//		fail("Not yet implemented");
	}

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

	@Test
	public void testRemoveFromServiceStarting() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
//		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
//		ConferenceRoom room3 = new ConferenceRoom("15-11", 20);
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

	@Test
	public void testCutoffIndex() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
//		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
//		ConferenceRoom room3 = new ConferenceRoom("15-11", 20);
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

	@Test
	public void testCancelLeaseByNumber() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
		Client client2 = new Client("Walker Clem", "waclem");
//		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
//		ConferenceRoom room3 = new ConferenceRoom("15-11", 20);
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

	@Test
	public void testAddLease() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
//		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		
		room.addLease(lease);
		String[] leases = room.listLeases();
		
		assertEquals(leases[0], "000010 | 2020-04-08 to 2020-04-15 | 18 | Conference Room: 23-11");
	}

	@Test
	public void testListLeases() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		
		Client client = new Client("Alex Raum", "maraum");
//		ConferenceRoom room2 = new ConferenceRoom("14-11", 20);
		LocalDate start = LocalDate.of(2020, 4, 8);
		LocalDate end = LocalDate.of(2020, 4, 15);
		int occupants = 18;
		Lease lease = new Lease(10, client, room, start, end, occupants);
		
		room.addLease(lease);
		String[] leases = room.listLeases();
		
		assertEquals("000010 | 2020-04-08 to 2020-04-15 | 18 | Conference Room: 23-11", leases[0]);
	}

	@Test
	public void testGetDescription() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		assertEquals("Conference Room: 23-11 | 20", room.getDescription());
		
		room.takeOutOfService();
		
		assertEquals("Conference Room: 23-11 | 20 Unavailable", room.getDescription());
	}

	@Test
	public void testEqualsObject() {
		ConferenceRoom room = new ConferenceRoom("23-11", 20);
		ConferenceRoom room2 = new ConferenceRoom("23-11", 20);
		ConferenceRoom room3 = new ConferenceRoom("23-11", 21);
		ConferenceRoom room4 = new ConferenceRoom("22-11", 20);
		ConferenceRoom room5 = new ConferenceRoom("23-12", 20);
		String test = new String();
		
		assertTrue(room.equals(room2));
		assertFalse(room.equals(null));
		assertFalse(room.equals(test));
		assertFalse(room.equals(room3));
		assertFalse(room.equals(room4));
		assertFalse(room.equals(room5));
		
		room2.takeOutOfService();
		
		assertFalse(room.equals(room2));
	}

}
