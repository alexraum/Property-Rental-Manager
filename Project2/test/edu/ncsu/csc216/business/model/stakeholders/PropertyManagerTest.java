/**
 * 
 */
package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.Office;

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
//		PropertyManager singleton = PropertyManager.getInstance();
//		Client client = new Client("Donald Knuth", "deknuth");
//		Office office = new Office("15-15", 50);
//		LocalDate start = LocalDate.of(2022, 1, 1);
//		LocalDate end = LocalDate.of(2022, 3, 31);
//		singleton.addLeaseFromFile(client, 1, office, start, end, 25);
//		assertEquals(1, singleton.listLeasesForRentalUnit(0).length);
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#cancelClientsLease(int, int)}.
	 */
	@Test
	public void testCancelClientsLease() {
		//PropertyManager singleton = PropertyManager.getInstance();
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#returnToService(int)}.
	 */
	@Test
	public void testReturnToService() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#removeFromService(int, java.time.LocalDate)}.
	 */
	@Test
	public void testRemoveFromService() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#closeRentalUnit(int)}.
	 */
	@Test
	public void testCloseRentalUnit() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#createLease(int, int, java.time.LocalDate, int, int)}.
	 */
	@Test
	public void testCreateLease() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listClients()}.
	 */
	@Test
	public void testListClients() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listClientLeases(int)}.
	 */
	@Test
	public void testListClientLeases() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listRentalUnits()}.
	 */
	@Test
	public void testListRentalUnits() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#listLeasesForRentalUnit(int)}.
	 */
	@Test
	public void testListLeasesForRentalUnit() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#getUnitAtLocation(java.lang.String)}.
	 */
	@Test
	public void testGetUnitAtLocation() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#filterRentalUnits(java.lang.String, boolean)}.
	 */
	@Test
	public void testFilterRentalUnits() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.stakeholders.PropertyManager#flushAllData()}.
	 */
	@Test
	public void testFlushAllData() {
		// fail("Not yet implemented");
	}

}
