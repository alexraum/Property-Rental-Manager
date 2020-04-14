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
public class OfficeTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#reserve(edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, int, int)}.
	 * @throws RentalCapacityException 
	 * @throws RentalDateException 
	 * @throws RentalOutOfServiceException 
	 */
	@Test
	public void testReserve() throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		Office office = new Office("22-11", 2);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end = LocalDate.of(2021, 4, 30);
		Lease lease = office.reserve(client, start, 1, 1);
		assertEquals(end, lease.getEnd());
		
		try {
			office.reserve(null, start, 1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(2, office.getCapacity());
		}
		
		try {
			office.reserve(client, start, 1, 3);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(2, office.getCapacity());
		}
		
		try {
			office.reserve(client, LocalDate.of(2020, 4, 10), 1, 1);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(2, office.getCapacity());
		}
		
		try {
			office.reserve(client, LocalDate.of(2020, 4, 26), 1, 3);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(2, office.getCapacity());
		}
		
		try {
			office.reserve(client, start, 1, 2);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(2, office.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#recordExistingLease(int, edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, java.time.LocalDate, int)}.
	 */
	@Test
	public void testRecordExistingLease() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#checkDates(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testCheckDates() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#removeFromServiceStarting(java.time.LocalDate)}.
	 */
	@Test
	public void testRemoveFromServiceStarting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#remainingCapacityFor(java.time.LocalDate)}.
	 */
	@Test
	public void testRemainingCapacityFor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#getMonthsDuration(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testGetMonthsDuration() {
		fail("Not yet implemented");
	}

}
