/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The HotelSuiteTest class checks the functionality of all methods in the 
 * HotelSuite class to ensure their proper implementation.
 * 
 * @author Alex Raum, Walker Clem
 */
public class HotelSuiteTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.HotelSuite#reserve(edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, int, int)}.
	 * @throws RentalCapacityException if the maximum capacity is exceeded
	 * @throws RentalDateException if the date is invalid
	 * @throws RentalOutOfServiceException if the unit is out of service
	 */
	@Test
	public void testReserve() throws RentalCapacityException, RentalDateException, RentalOutOfServiceException {
		HotelSuite suite = new HotelSuite("22-11", 2);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 5);
		LocalDate end = LocalDate.of(2020, 4, 12);
		Lease lease = suite.reserve(client, start, 1, 1);
		assertEquals(end, lease.getEnd());
		
		try {
			suite.reserve(null, start, 1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
		
		try {
			suite.reserve(client, start, 1, 3);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
		
		try {
			suite.reserve(client, LocalDate.of(2020, 4, 10), 1, 1);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
		
		try {
			suite.reserve(client, LocalDate.of(2020, 4, 26), 1, 3);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.HotelSuite#recordExistingLease(int, edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, java.time.LocalDate, int)}.
	 * @throws RentalCapacityException if the maximum capacity is exceeded
	 * @throws RentalDateException if the date is invalid
	 */
	@Test
	public void testRecordExistingLease() throws RentalCapacityException, RentalDateException {
		HotelSuite suite = new HotelSuite("22-11", 2);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2020, 4, 5);
		LocalDate end = LocalDate.of(2020, 4, 12);
		
		Lease lease = suite.recordExistingLease(0, client, start, end, 1);
		assertEquals(0, lease.getConfirmationNumber());
		assertEquals(client, lease.getClient());
		assertEquals(start, lease.getStart());
		assertEquals(end, lease.getEnd());
		assertEquals(1, lease.getNumOccupants());
		
		try {
			suite.recordExistingLease(0, client, start, end, 3);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
		
		try {
			suite.recordExistingLease(0, client, start, start.plusDays(4), 1);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, suite.getFloor());
			assertEquals(11, suite.getRoom());
			assertEquals(2, suite.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.HotelSuite#checkDates(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testCheckDates() {
		HotelSuite suite1 = new HotelSuite("22-11", 2);
		
		try {
			suite1.checkDates(LocalDate.of(1989, 2, 6), LocalDate.of(2029, 12, 31));
			fail();
		} catch (RentalDateException e) {
			assertEquals("Lease date cannot start before 2020-01-01", e.getMessage());
		}
		
		try {
			suite1.checkDates(LocalDate.of(2020, 1, 1), LocalDate.of(2030, 1, 1));
			fail();
		} catch (RentalDateException e) {
			assertEquals("Lease date cannot end after 2029-12-31", e.getMessage());
		}
		
		try {
			suite1.checkDates(LocalDate.of(2022, 11, 29), LocalDate.of(2022, 7, 7));
			fail();
		} catch (RentalDateException e) {
			assertEquals("Start date for lease cannot be after the end date", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.HotelSuite#removeFromServiceStarting(java.time.LocalDate)}.
	 * @throws RentalCapacityException if the maximum capacity is exceeded
	 * @throws RentalDateException if the date is invalid
	 */
	@Test
	public void testRemoveFromServiceStarting() throws RentalCapacityException, RentalDateException {
		HotelSuite suite1 = new HotelSuite("22-11", 2);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start1 = LocalDate.of(2020, 4, 5);
		LocalDate end1 = LocalDate.of(2020, 4, 12);
		
		LocalDate start2 = LocalDate.of(2020, 5, 3);
		LocalDate end2 = LocalDate.of(2020, 5, 17);
		
		LocalDate start3 = LocalDate.of(2020, 5, 24);
		LocalDate end3 = LocalDate.of(2020, 5, 31);
		
		Lease lease1 = new Lease(client, suite1, start1, end1, 1);
		Lease lease2 = new Lease(client, suite1, start2, end2, 1);
		Lease lease3 = new Lease(client, suite1, start3, end3, 1);
		
		suite1.addLease(lease1);
		suite1.addLease(lease2);
		suite1.addLease(lease3);
		
		LocalDate cutoffDate = LocalDate.of(2020, 5, 12);
		SortedList<Lease> tail = suite1.removeFromServiceStarting(cutoffDate);
		assertEquals(1, tail.size());
		assertEquals(2, suite1.listLeases().length);
	}
}
