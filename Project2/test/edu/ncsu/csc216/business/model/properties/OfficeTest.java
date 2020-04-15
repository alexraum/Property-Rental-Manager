/**
 * 
 */
package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

//import edu.ncsu.csc216.business.list_utils.SimpleListIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * The OfficeTest class checks the methods defined in the Office class
 * to ensure their proper functionality.
 * 
 * @author Alex Raum, Walker Clem
 */
public class OfficeTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#reserve(edu.ncsu.csc216.business.model.stakeholders.Client, java.time.LocalDate, int, int)}.
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date or computed end dates are not valid
	 * @throws RentalOutOfServiceException if the office is currently out of service
	 */
	@Test
	public void testReserve() throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		Office office = new Office("22-11", 2);
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end = LocalDate.of(2021, 4, 30);
		Lease lease = office.reserve(client, start, 1, 1);
//		Lease lease2 = office.reserve(client, start, 1, 20);
//		Lease lease3 = office.reserve(client, start, 1, 30);
//		SimpleListIterator<Lease> iterator = office.myLeases.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNumOccupants());
//		}
		
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
	 * 
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date is not the first day of the
	 * month and the end date is not the last day of the month
	 */
	@Test
	public void testRecordExistingLease() throws RentalCapacityException, RentalDateException {
		Office office = new Office("22-11", 30);
		int confirmationNumber = 1;
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end = LocalDate.of(2021, 4, 30);
		int numOccupants = 10;
		
		Lease lease = office.recordExistingLease(confirmationNumber, client, start, end, numOccupants);
		assertEquals(end, lease.getEnd());
		
		try {
			office.recordExistingLease(confirmationNumber, client, start, end, 31);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());
		}
		
		try {
			office.recordExistingLease(2, client, start, end, 21);
			fail();
		} catch (RentalCapacityException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#checkDates(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testCheckDates() {
		Office office = new Office("22-11", 30);
		LocalDate validStart = LocalDate.of(2020, 02, 06);
		LocalDate invalidStart = LocalDate.of(2019, 02, 06);
		LocalDate validEnd = LocalDate.of(2029, 11, 29);
		LocalDate invalidEnd = LocalDate.of(2030, 11, 29);
		
		try {
			office.checkDates(invalidStart, validEnd);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());
		}
		
		try {
			office.checkDates(validStart, invalidEnd);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());
		}
		
		try {
			office.checkDates(validEnd, validStart);
			fail();
		} catch (RentalDateException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());
		}
	}

	/**
	 * Checks the proper functionality of the cancelLeaseByNumber method.
	 * 
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date is not the first day of the
	 * month and the end date is not the last day of the month
	 */
	@Test
	public void testCancelLeaseByNumber() throws RentalDateException, RentalCapacityException {
		Office office = new Office("22-11", 30);
		int confirmationNumber = 1;
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end = LocalDate.of(2021, 4, 30);
		int numOccupants = 10;
		
		Lease lease = office.recordExistingLease(confirmationNumber, client, start, end, numOccupants);
		assertEquals(lease, office.cancelLeaseByNumber(confirmationNumber));
		
		try {
			office.cancelLeaseByNumber(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(22, office.getFloor());
			assertEquals(11, office.getRoom());
			assertEquals(30, office.getCapacity());	
		}
	}
	
//	/**
//	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#removeFromServiceStarting(java.time.LocalDate)}.
//	 */
//	@Test
//	public void testRemoveFromServiceStarting() {
	    // TODO error stems from while loop and how I'm moving date back, try adding multiple leases to the list
	    // then truncate the end date for each lease in the list.  (to resolve IllegalArgumentException failure)
//		//fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		Office office = new Office("22-11", 30);
		assertEquals("Office:          22-11 |  30", office.getDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#remainingCapacityFor(java.time.LocalDate)}.
	 * 
	 * @throws RentalCapacityException if the office cannot hold the number of 
	 * occupants over the dates of the proposed lease
	 * @throws RentalDateException if the start date is not the first day of the
	 * month and the end date is not the last day of the month
	 */
	@Test
	public void testRemainingCapacityFor() throws RentalCapacityException, RentalDateException {
		Office office = new Office("22-11", 30);
		int confirmationNumber = 1;
		Client client = new Client("Alex Raum", "maraum");
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end = LocalDate.of(2021, 4, 30);
		LocalDate checkDate = LocalDate.of(2021, 4, 15);
		int numOccupants = 10;
		
		office.recordExistingLease(confirmationNumber, client, start, end, numOccupants);
		assertEquals(20, office.remainingCapacityFor(checkDate));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.properties.Office#getMonthsDuration(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testGetMonthsDuration() {
		LocalDate start = LocalDate.of(2021, 4, 1);
		LocalDate end1 = LocalDate.of(2021, 4, 30);
		LocalDate end2 = LocalDate.of(2022, 4, 30);
		assertEquals(1, Office.getMonthsDuration(start, end1));
		assertEquals(13, Office.getMonthsDuration(start, end2));
	}
}
