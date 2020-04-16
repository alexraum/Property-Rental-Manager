/**
 * 
 */
package edu.ncsu.csc216.business.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;
import edu.ncsu.csc216.business.model.stakeholders.DuplicateClientException;
import edu.ncsu.csc216.business.model.stakeholders.DuplicateRoomException;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * Rental Reader class
 * @author Alex Raum, Walker Clem
 *
 */
public class RentalReader {
	
	static PropertyManager manager = PropertyManager.getInstance();
	
	/**
	 * Reads the rental data
	 * @param filename the filename to read from
	 * @throws DuplicateClientException 
	 * @throws DuplicateRoomException 
	 */
	public static void readRentalData(String filename) {
		manager.flushAllData();
		try {

			Scanner fileReader = new Scanner(new File(filename));
			if (!filename.substring(filename.length() - 3, filename.length()).equals(".md")) {
				fileReader.close();
				throw new IllegalArgumentException();
			}
			
			Client lastClient = null;
			while (fileReader.hasNextLine()) {
				String next = fileReader.nextLine();
				if (!next.isBlank()) {
					if (next.startsWith("H") || next.startsWith("C") || next.startsWith("O")) {
						try {
							rentalUnitReader(next);
						} catch (DuplicateRoomException e) {}
					} else if (next.startsWith("#")) {
						try {
							lastClient = clientReader(next);
						} catch (DuplicateClientException e) {}
					} else if (Character.isDigit(next.trim().charAt(0))) {
						leaseReader(next, lastClient);
					}
				} else {
					continue;
				}
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	private static Client clientReader(String next) throws DuplicateClientException {
		Client c = null;
		Scanner clientReader = new Scanner(next);

		clientReader.useDelimiter("\\(|\\)");
		String fullName = clientReader.next().trim().substring(1);
		String id = clientReader.next();

		c = new Client(fullName, id);
		
		clientReader.close();
		
		try {
			manager.addNewClient(fullName, id);
		} catch (DuplicateClientException e) {}
		
		return c;
	}

	private static void leaseReader(String line, Client c) {
		Scanner leaseReader = new Scanner(line);
		leaseReader.useDelimiter("\\|");
		String id = leaseReader.next().replaceAll("\\s", "");
		String dates = leaseReader.next();
		String startDate = dates.substring(1, 11);
		String endDate = dates.substring(15, dates.length() - 1);
		String occupants = leaseReader.next().replaceAll("\\s", "");
		
		Scanner desc = new Scanner(leaseReader.next());
		desc.useDelimiter(":");
		desc.next();
		String location = desc.next().substring(1).trim();
		desc.close();
		
		leaseReader.close();
		RentalUnit ru = null;
		ru = manager.getUnitAtLocation(location);
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		manager.addLeaseFromFile(c, Integer.parseInt(id), ru, start, end, Integer.parseInt(occupants));
	}

	private static RentalUnit rentalUnitReader(String line) throws DuplicateRoomException {
		RentalUnit a = null;

		Scanner rentalUnitReader = new Scanner(line); // makes a reader for the first line
		rentalUnitReader.useDelimiter("\\|");

		Scanner fh = new Scanner(rentalUnitReader.next());
		fh.useDelimiter(":");
		String type = fh.next();
		String location = fh.next().replaceAll("\\s", "");
		fh.close();
		
		Scanner sh = new Scanner(rentalUnitReader.next());
		int capacity = sh.nextInt();
		
		try {
			a = manager.addNewUnit(type, location, capacity);
		} catch (DuplicateRoomException e) {}
		
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