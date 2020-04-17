/**
 * 
 */
package edu.ncsu.csc216.business.model.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * The RentalWriterTest class checks for the correct functionality
 * of the methods in the RentalWriter class
 * 
 * @author Walker Clem, Alex Raum
 */
public class RentalWriterTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.io.RentalWriter#writeRentalFile(java.lang.String)}.
	 */
	@Test
	public void testWriteRentalFile() {
		RentalReader.readRentalData("test-files/test.md");
		RentalWriter.writeRentalFile("test-files/output.md");
		PropertyManager a = PropertyManager.getInstance();
		assertEquals(a.listClients()[0], "Amanda Smith (a12#smL)");
	}

}
