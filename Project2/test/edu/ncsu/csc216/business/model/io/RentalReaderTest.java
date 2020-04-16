/**
 * 
 */
package edu.ncsu.csc216.business.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.business.model.stakeholders.DuplicateClientException;
import edu.ncsu.csc216.business.model.stakeholders.DuplicateRoomException;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * @author Alex Raum
 *
 */
public class RentalReaderTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.business.model.io.RentalReader#readRentalData(java.lang.String)}.
	 * @throws DuplicateClientException 
	 * @throws DuplicateRoomException 
	 */
	@Test
	public void testReadRentalData() throws DuplicateClientException, DuplicateRoomException {
		//fail("Not yet implemented");
		RentalReader.readRentalData("test-files/test.md");
		
		try {
			RentalReader.readRentalData("test-files/test");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Unable to load file.");
		}
	}

}
