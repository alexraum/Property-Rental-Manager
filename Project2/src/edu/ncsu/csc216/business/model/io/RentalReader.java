/**
 * 
 */
package edu.ncsu.csc216.business.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * Rental Reader class
 * @author Alex Raum, Walker Clem
 *
 */
public class RentalReader {
	/**
	 * Reads the rental data
	 * @param filename the filename to read from
	 */
	public static void readRentalData(String filename) {
		try {
			Scanner fileReader = new Scanner(new File(filename));
			if (!filename.substring(filename.length() - 3, filename.length()).equals(".md")) {
				fileReader.close();
				throw new IllegalArgumentException();
			}
			SimpleArrayList<RentalUnit> units = new SimpleArrayList<RentalUnit>();
			int i = 0;
			while(fileReader.hasNextLine()) {
				units.add(rentalUnitReader(fileReader.nextLine()));
				System.out.println(units.get(i).getDescription());
				i++;
			}
			
			fileReader.close();
			return;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	public static RentalUnit rentalUnitReader(String line) {
		RentalUnit a = null;

		Scanner rentalUnitReader = new Scanner(line); // makes a reader for the first line
		rentalUnitReader.useDelimiter("\\|");

		Scanner fh = new Scanner(rentalUnitReader.next());
		fh.useDelimiter(":");
		String type = fh.next(); // gets the type with colon
		System.out.println("type: " + type);
		String location = fh.next().replaceAll("\\s", "");
		System.out.println("location: " + location);
		fh.close();
		
		Scanner sh = new Scanner(rentalUnitReader.next());
		int capacity = sh.nextInt();
		System.out.println("capacity: " + capacity);
		if (type.equals("Office")) {
			a = new Office(location, capacity);
		}
		else if (type.equals("Conference Room")) {
			a = new ConferenceRoom(location, capacity);
		}
		else if (type.equals("Hotel Suite")) {
			a = new HotelSuite(location, capacity);
		}
		if (rentalUnitReader.hasNext()) {
			if (sh.next().equals("Unavailable")) {
				a.takeOutOfService();
			}
		}
		sh.close();

		rentalUnitReader.close();
		return a;
	}
}
