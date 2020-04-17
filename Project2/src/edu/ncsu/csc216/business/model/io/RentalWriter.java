/**
 * 
 */
package edu.ncsu.csc216.business.model.io;


import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

//import java.io.IOException;

/**
 * The RentalWriter class writes existing system data to a
 * specified output data. It uses a single instance of 
 * PropertyManager to obtain all necessary date to be written.
 * 
 * @author Walker Clem, Alex Raum
 */
public class RentalWriter {
	
	/**
	 * Static method used to write the system data to a 
	 * specified file. 
	 * 
	 * @param filename the name of the file that data is to be written to
	 */
	public static void writeRentalFile(String filename) {
		PropertyManager manager = PropertyManager.getInstance();
		try {
			// PrintWriter fileWriter = new PrintWriter(new File(filename));
			PrintStream fileWriter = new PrintStream(new File(filename));
			for (String unit : manager.listRentalUnits()) {
				fileWriter.println(unit);
			}
			fileWriter.println();
			int i = 0;
			for (String client : manager.listClients()) {
				fileWriter.println("#" + client);
				for (String lease : manager.listClientLeases(i)) {
					fileWriter.println("   " + lease);
				}
				i++;
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
