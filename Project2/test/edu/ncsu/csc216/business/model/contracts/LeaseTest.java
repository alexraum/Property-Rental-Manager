/**
 * 
 */
package edu.ncsu.csc216.business.model.contracts;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

/**
 * LeaseTest provides methods to check the functionality of the Lease class. 
 *
 * @author Alex Raum
 */
public class LeaseTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#setEndDateEarlier(java.time.LocalDate)}.
	 */
	@Test
	public void testSetEndDateEarlier() {
		LocalDate start = LocalDate.of(1989, 2, 6);
		LocalDate end = LocalDate.of(2020, 4, 6);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#leaseData()}.
	 */
	@Test
	public void testLeaseData() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#resetConfirmationNumbering(int)}.
	 */
	@Test
	public void testResetConfirmationNumbering() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.contracts.Lease#compareTo(edu.ncsu.csc216.business.model.contracts.Lease)}.
	 */
	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
