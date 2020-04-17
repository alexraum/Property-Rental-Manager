/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * @author Alex Raum
 *
 */
public class PropertyManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		PropertyManager singleton = PropertyManager.getInstance();
		assertTrue(singleton instanceof PropertyManager);
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#addNewClient(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddNewClient() throws DuplicateClientException {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		singleton.addNewClient("Donald Knuth", "deknuth");
		assertEquals("Donald Knuth (deknuth)", singleton.listClients()[0]);
		
		try {
			singleton.addNewClient("Donald Knuth", "deknuth");
			fail();
		} catch (DuplicateClientException e) {
			assertEquals("Donald Knuth (deknuth)", singleton.listClients()[0]);	
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#addNewUnit(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewUnit() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		try {
			singleton.addNewUnit("Conference Room", "12-14", 10);
			singleton.addNewUnit("Hotel Suite", "12-15", 1);
			singleton.addNewUnit("Office", "12-16", 100);
			singleton.addNewUnit("Conference Room", "12-14", 10);
			fail();
		} catch (DuplicateRoomException e) {
			assertEquals(3, singleton.listRentalUnits().length);
			assertEquals("Rental Unit at this location already exists", e.getMessage());
		}
		
		try {
			singleton.addNewUnit("Apartment", "12-17", 2);
			fail();
		} catch (DuplicateRoomException | IllegalArgumentException e) {
			assertEquals("Invalid Rental Unit type", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#addLeaseFromFile(edu.ncsu.csc216.business.model.stakeholders.Client, int, edu.ncsu.csc216.business.model.properties.RentalUnit, java.time.LocalDate, java.time.LocalDate, int)}.
	 */
	@Test
	public void testAddLeaseFromFile() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		Client client = new Client("Walker Clem", "waclem");
		Office office = new Office("15-15", 50);
		LocalDate start = LocalDate.of(2022, 1, 1);
		LocalDate end = LocalDate.of(2022, 3, 31);
		try {
			singleton.addNewClient("Walker Clem", "waclem");
			singleton.addNewUnit("Office", "15-15", 50);
			singleton.addLeaseFromFile(client, 1, office, start, end, 25);
		} catch (DuplicateClientException | DuplicateRoomException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(1, singleton.listRentalUnits().length);
		}

		
		assertEquals(1, singleton.listClients().length);
		assertEquals(1, singleton.listRentalUnits().length);
		
		try {
			singleton.addLeaseFromFile(client, 1, office, LocalDate.of(1980, 11, 29), end, 25);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(1, singleton.listRentalUnits().length);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#cancelClientsLease(int, int)}.
	 */
	@Test
	public void testCancelClientsLease() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		LocalDate start = LocalDate.of(2022, 1, 1);
		LocalDate end = LocalDate.of(2022, 3, 31);
		LocalDate start2 = LocalDate.of(2023, 1, 1);
		LocalDate end2 = LocalDate.of(2023, 3, 31);
		
		try {
			Client client = singleton.addNewClient("Walker Clem", "waclem");
			RentalUnit unit = singleton.addNewUnit("Office", "15-15", 50);
			RentalUnit unit2 = singleton.addNewUnit("Office", "15-16", 50);
			singleton.addLeaseFromFile(client, 1, unit, start, end, 25);
			singleton.addLeaseFromFile(client, 2, unit2, start2, end2, 25);
			singleton.cancelClientsLease(0, 1);
			assertEquals(1, singleton.listClientLeases(0).length);
		} catch (DuplicateClientException | DuplicateRoomException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(2, singleton.listRentalUnits().length);
		}
		
		try {
			singleton.cancelClientsLease(-1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(2, singleton.listRentalUnits().length);
		}
		
		try {
			singleton.cancelClientsLease(0, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(2, singleton.listRentalUnits().length);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#returnToService(int)}.
	 */
	@Test
	public void testReturnToService() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#removeFromService(int, java.time.LocalDate)}.
	 */
	@Test
	public void testRemoveFromService() {
//		PropertyManager singleton = PropertyManager.getInstance();
//		singleton.flushAllData();
//
//		try {
//			singleton.addNewUnit("Conference Room", "12-14", 10);
//			singleton.addNewUnit("Hotel Suite", "12-15", 1);
//			singleton.addNewUnit("Office", "12-16", 100);
//			singleton.addNewClient("Donald Knuth", "deknuth");
//		} catch (DuplicateClientException | DuplicateRoomException e) {
//			assertEquals(3, singleton.listRentalUnits().length);
//			assertEquals("Rental Unit at this location already exists", e.getMessage());
//		}
//		singleton.createLease(0, 0, LocalDate.of(2020, 7, 7), 7, 10);
//		singleton.removeFromService(0, LocalDate.of(2020, 7, 10));
//		singleton.filterRentalUnits("", true);
//		
//		assertEquals(0, singleton.listRentalUnits().length);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#closeRentalUnit(int)}.
	 */
	@Test
	public void testCloseRentalUnit() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		
		try {
			singleton.addNewUnit("Conference Room", "12-14", 10);
			singleton.addNewUnit("Hotel Suite", "12-15", 1);
			singleton.addNewUnit("Office", "12-16", 100);
		} catch (DuplicateRoomException e) {
			assertEquals(3, singleton.listRentalUnits().length);
			assertEquals("Rental Unit at this location already exists", e.getMessage());
		}
		
		singleton.closeRentalUnit(0);
		assertEquals(2, singleton.listRentalUnits().length);
		
		try {
			singleton.closeRentalUnit(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, singleton.listRentalUnits().length);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#createLease(int, int, java.time.LocalDate, int, int)}.
	 */
	@Test
	public void testCreateLease() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listClients()}.
	 */
	@Test
	public void testListClients() throws DuplicateClientException {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		singleton.addNewClient("Donald Knuth", "deknuth");
		singleton.addNewClient("Walker Clem", "waclem");
		singleton.addNewClient("Alex Raum", "maraum");
		
		assertEquals(3, singleton.listClients().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listClientLeases(int)}.
	 */
	@Test
	public void testListClientLeases() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		LocalDate start = LocalDate.of(2022, 1, 1);
		LocalDate end = LocalDate.of(2022, 3, 31);
		
		try {
			Client client = singleton.addNewClient("Walker Clem", "waclem");
			RentalUnit unit = singleton.addNewUnit("Office", "15-15", 50);
			singleton.addLeaseFromFile(client, 1, unit, start, end, 25);
			assertEquals(1, singleton.listClientLeases(0).length);
		} catch (DuplicateClientException | DuplicateRoomException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(1, singleton.listRentalUnits().length);
		}
		
		try {
			singleton.listClientLeases(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, singleton.listClientLeases(0).length);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listRentalUnits()}.
	 */
	@Test
	public void testListRentalUnits() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listLeasesForRentalUnit(int)}.
	 */
	@Test
	public void testListLeasesForRentalUnit() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		LocalDate start = LocalDate.of(2022, 1, 1);
		LocalDate end = LocalDate.of(2022, 3, 31);
		LocalDate start2 = LocalDate.of(2023, 1, 1);
		LocalDate end2 = LocalDate.of(2023, 3, 31);
		
		try {
			Client client = singleton.addNewClient("Walker Clem", "waclem");
			RentalUnit unit = singleton.addNewUnit("Office", "15-15", 50);
			singleton.addLeaseFromFile(client, 1, unit, start, end, 25);
			singleton.addLeaseFromFile(client, 2, unit, start2, end2, 25);
			assertEquals(2, singleton.listLeasesForRentalUnit(0).length);
		} catch (DuplicateClientException | DuplicateRoomException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(1, singleton.listRentalUnits().length);
		}
		
		try {
			assertEquals(2, singleton.listLeasesForRentalUnit(-1).length);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, singleton.listClients().length);
			assertEquals(1, singleton.listRentalUnits().length);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#getUnitAtLocation(java.lang.String)}.
	 */
	@Test
	public void testGetUnitAtLocation() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		
		try {
			singleton.addNewUnit("Conference Room", "12-14", 10);
			singleton.addNewUnit("Hotel Suite", "12-15", 1);
			singleton.addNewUnit("Office", "12-16", 100);
		} catch (DuplicateRoomException e) {
			assertEquals(3, singleton.listRentalUnits().length);
			assertEquals("Rental Unit at this location already exists", e.getMessage());
		}
		
		RentalUnit room = singleton.getUnitAtLocation("12-14");
		assertEquals(12, room.getFloor());
		assertEquals(14, room.getRoom());
		assertEquals(10, room.getCapacity());
		
		try {
			singleton.getUnitAtLocation("12-17");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(12, room.getFloor());
			assertEquals(14, room.getRoom());
			assertEquals(10, room.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#filterRentalUnits(java.lang.String, boolean)}.
	 */
	@Test
	public void testFilterRentalUnits() {
		PropertyManager singleton = PropertyManager.getInstance();
		singleton.flushAllData();
		
		try {
			singleton.addNewUnit("Conference Room", "12-14", 10);
			singleton.addNewUnit("Hotel Suite", "12-15", 1);
			singleton.addNewUnit("Office", "12-16", 100);
		} catch (DuplicateRoomException e) {
			assertEquals(3, singleton.listRentalUnits().length);
			assertEquals("Rental Unit at this location already exists", e.getMessage());
		}
		
		singleton.filterRentalUnits("c", true);
		assertEquals(1, singleton.listRentalUnits().length);
		singleton.filterRentalUnits("", false);
		
		singleton.filterRentalUnits("h", true);
		assertEquals(1, singleton.listRentalUnits().length);
		singleton.filterRentalUnits("", false);
		
		singleton.filterRentalUnits("o", true);
		assertEquals(1, singleton.listRentalUnits().length);
		singleton.filterRentalUnits("", false);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#flushAllData()}.
	 */
	@Test
	public void testFlushAllData() {
		fail("Not yet implemented");
	}

}
