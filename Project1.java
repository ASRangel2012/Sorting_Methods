import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * COP 3530: Project 1 - Array Searches and Sorts
 * <p>
 * Prompts user for file name, Countries.csv & as input args to the system
 * Countries.csv will have a header 1st line, giving identifying paramaters for
 * the country object such as name, code,capitol,population, GDP and
 * happinessRank. This class will parse thru the csv and create an array of
 * countries objects. It will prompt the user with a menu for printing, sorting
 * and finding a specific country. Sort implementation methods vary from
 * insertion,selection, bubble, binary search and sequential search.
 * <p>
 * 
 * @author Angel Selva Rodriguez
 * @version 06/27/2020
 */

public class Project1 {
	public static Scanner userInput = new Scanner(System.in);// for input from user
	public static Scanner getCountry = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			System.out.println("Please enter file name: ");
			String fileName = userInput.next();
			while (!fileName.equalsIgnoreCase("Countries1.csv")) {
				System.out.println("Please input correct file name...should be 'Countries1.csv'... ");
				fileName = userInput.next();
				break;
			}
			List<Country> list = readFile(fileName);
			final Country[] countryArray = list.toArray(new Country[list.size()]);
			int choice = -1;
			while (choice != 6) {
				menuOptions();
				try {
					choice = userInput.nextInt();
					switch (choice) {
					case 1:
						printCountries(countryArray);
						System.out.println();
						break;
					case 2:
						sortByName(countryArray);
						printCountries(countryArray);
						System.out.println();
						// System.out.println("Sorted by A - Z ");
						break;
					case 3:
						sortByHappy(countryArray);
						printCountries(countryArray);
						System.out.println();
						System.out.println("Sorted by Happiness Rank!");
						break;
					case 4:
						sortByGDP(countryArray);
						printCountries(countryArray);
						System.out.println();
						System.out.println("Sorted by GDP per capita");
						break;
					case 5:
						System.out.println("Please enter desired country to search for ");
						// get user choice
						// userInput.next();
						// String country = userInput.nextLine(); only works if user types in 5 United
						// States
						String country = getCountry.nextLine();// created separated scanner for getting country and all
																// is well!
						locateCountry(countryArray, country);
						break;
					case 6:
						System.out.println("Goodbye!");
						break;
					default:
						break;
					}

				} catch (InputMismatchException Ex) {
					System.out.println("Must be an integer between 1 - 6, anything else does not work! ");
					userInput.next();
				}
			} // end while true

		} catch (IOException e) {
			e.printStackTrace();

		}

	}// end MAIN
		// -------------------------------------------------------------------------------------------------------
		// HELPER METHODS BELOW

	// References
	/*
	 * https://docs.oracle.com/javase/8/docs/api/java/util/List.html
	 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html
	 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
	 */

	/**
	 * This method creates a LIST of type Country objects via user input file. It
	 * takes the file name give and creates the full path using Path. Once the file
	 * path is located, this method uses try-with-rss to auto close the buffer reads
	 * the entire line by line and discards the headers.
	 * 
	 * @parameter accepts fileName, string inputed by user.
	 * @returns the country array
	 */
	public static List<Country> readFile(String fileName) throws IOException {
		List<Country> countries = new ArrayList<>();
		Path findPath = Paths.get(fileName);
		try (BufferedReader buffy = Files.newBufferedReader(findPath, StandardCharsets.UTF_8)) {
			@SuppressWarnings("unused") // get rid of headers in csv file
			String lineHeader = buffy.readLine();
			String line = buffy.readLine();
			while (line != null) {
				// create array of the incoming file
				String[] countryArray = line.split(",");
				Country country = createEntry(countryArray);// use helper to create a new country object with correct
															// parameters
				countries.add(country);
				line = buffy.readLine();// null ends while loop
			}
		}
		return countries;
	}

	/**
	 * This method uses the string[] array provided when parsing the csv file and
	 * correctly initializes the country object with it's parameters as they appear
	 * in the csv file
	 * 
	 * @param info array that has the parsed csv file
	 * @return new instantiated Country object
	 */
	public static Country createEntry(String[] info) {
		String countryName = info[0];
		String countryCode = info[1];
		String countryCap = info[2];
		String countryPop = info[3];

		String countryGDP = info[4];
		String countryHappyRank = info[5];
		return new Country(countryName, countryCode, countryCap, countryPop, countryGDP, countryHappyRank);
	}

	/**
	 * This is a simple void menu method which prints the menu options to console,
	 * utilizing printf for small formatting
	 * 
	 */
	public static void menuOptions() {
		System.out.printf("%n --------------------------------------------------------------------------------");
		// System.out.printf("%n Please choice of the following options");
		// System.out.printf("%n 0.Choose 0 to start program");
		System.out.printf("%n 1.Print all countries");
		System.out.printf("%n 2.Sort countries by name");
		System.out.printf("%n 3.Sort countries by happyniess rank");
		System.out.printf("%n 4.Sort countries by GDP per Capita");
		System.out.printf("%n 5.Locate and print specific country data");
		System.out.printf("%n 6.EXIT");
		System.out.printf("%n ----------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
	}

	/**
	 * This method prints out the countries in correctly and aligned fashion as per
	 * project 1 specifications. Utilizes printF and system.out.Format to correctly
	 * align said country output
	 * 
	 * @param Country[] array that holds all country objects
	 * 
	 * 
	 */
	public static void printCountries(Country[] array) {
		System.out.printf("%30s %20s %20s %20s %20s %20s", "Name", "Code", "Capitol", "Population", "GDP",
				"Happiness Rank");
		System.out.println();
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < array.length; i++) {
			System.out.format("%30s %20s %20s %20d %20s %20d", array[i].getName(), array[i].getCode(),
					array[i].getCapitol(), array[i].getPopulation(), array[i].getGDP(), array[i].getHappyRank());
			System.out.println();
		}
	}

	/**
	 * This method uses insertion sort to lexicographically sort the country objects
	 * by name, in ascending order. It begins by establishing a temp country object,
	 * located at the 2nd obj of the country array. While the value of integer j is
	 * greater than 0 AND country @ index j - 1 compared to temp > 0, swap the
	 * values of the country obj
	 *
	 *
	 * @param array
	 */
	// insertion sort method
	public static void sortByName(Country[] array) {
		// start at 2nd country
		for (int i = 1; i < array.length; i++) {
			Country temp = array[i];// compare to this value
			int j = i;// start at the same idx
			while (j > 0 && array[j - 1].compareTo(temp) > 0) {
				// swap country objects
				array[j] = array[j - 1];
				j--;// decrement
			}
			array[j] = temp;// reassign temp
		}
	}

	/**
	 * This method sorts the list by happpy rank using selection sort. First iterate
	 * thru the array and find the lowest ranked country, save this location
	 * (minIDX) for later use iterate once more thru array from the 2 val forward
	 * comparing it to the previous in the array. Repeat this cycle until all that
	 * are min values show up first and higher values are shifted to the right...
	 * Once this is complete, swap the values of the country objects.
	 * 
	 * @param Country[] array, holds country objects
	 */
	private static void sortByHappy(Country[] array) {
		// iterate thru array and find the lowest ranked country
		for (int i = 0; i < array.length - 1; i++) {
			// locate lowest GDP, assume it's the first element in the array
			int minIDX = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].getHappyRank() < array[minIDX].getHappyRank()) {
					minIDX = j;// found smaller
				}
			}
			// Swap country placement
			Country lowRank = array[minIDX];
			array[minIDX] = array[i];
			array[i] = lowRank;
		}
	}

	/**
	 * This method sorts the list by GDP using bubble sort. First iterate thru the
	 * array and compare elements as it progresses Iterate from the second object
	 * forward, comparing the previous one with the current one if(current is less
	 * than previous, swap values
	 * 
	 * @param Country[] array, holds country objects
	 */
	private static void sortByGDP(Country[] array) {
		for (int i = 0; i < array.length; i++) {
			// iterate thru from beginning to end, comparing elements as you go along
			for (int j = 1; j < array.length - 1; j++) {
				if (array[j - 1].getGDP() < array[j].getGDP()) {
					// swap here
					Country temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/**
	 * Helper method to verify if an array is lexicographically sorted. Accomplishes
	 * this by iterating thru the array until n-1 terms and verifying that each
	 * current value is less than the next value
	 * 
	 * @param Country[] array, holds country objects
	 * @return true or false, depending on the given array parameter
	 */
	private static boolean checkOrder(Country[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i].compareTo(array[i + 1]) > 0)
				return false;
		}
		return true;
	}

	/**
	 * Binary search method is used when the array is sorted, used country array and
	 * string key given by user. Start by initializing the vars startIDX, endIDX and
	 * midIDX. This is used to be able to use the divide and conquer approach of BS.
	 * The best case would be looking at the middle and finding your key object, in
	 * which case you print the object if not, compare the value of the current obj
	 * to the key and if it is less than the middle object, move to the left if
	 * greater, move to the right. Keep this approach until object is found or not
	 * found.
	 * 
	 * @param Country[] array, holds country objects
	 * @param String    Key, input string value from user to obtain desired country
	 * 
	 */
	private static void binarySearch(Country[] array, String key) {
		int startIDX = 0;
		int endIDX = array.length - 1;
		int midIDX = (0 + endIDX) / 2;
		while (startIDX <= endIDX) {
			if (array[midIDX].getName().compareToIgnoreCase(key) == 0) {
				System.out.println("Name: " + array[midIDX].getName());
				System.out.println("Code: " + array[midIDX].getCode());
				System.out.println("Capital: " + array[midIDX].getCapitol());
				System.out.println("Population: " + array[midIDX].getPopulation());
				System.out.println("GDP: " + array[midIDX].getGDP());
				System.out.println("Happiness Rank: " + array[midIDX].getHappyRank());
				break;
			} else if (array[midIDX].getName().compareToIgnoreCase(key) < 0) {
				// if at middle and it is less than this value, go right
				startIDX = midIDX + 1;
				// System.out.println("Located: " + array[midIDX].toString());
			} else {
				endIDX = midIDX - 1;
				// System.out.println("Located: " + array[midIDX].toString());
			}
			midIDX = (startIDX + endIDX) / 2;
			// System.out.println("Located: " + array[midIDX].toString());
		}
		if (startIDX > endIDX) {
			// key not found
			System.out.println("Did not locate desired country, please try again.");
		}

	}// end binarySearch

	/**
	 * This method compares the values in order from index 0 to n-1 until key is
	 * found, if not then country objects is not located
	 * 
	 * @param Country[] array, holds country objects
	 * @param String    Key, input string value from user to obtain desired country
	 * 
	 */
	private static void searchSequential(Country[] array, String key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].getName().equalsIgnoreCase(key)) {
				System.out.println("Name: " + array[i].getName());
				System.out.println("Code: " + array[i].getCode());
				System.out.println("Capital: " + array[i].getCapitol());
				System.out.println("Population: " + array[i].getPopulation());
				System.out.println("GDP: " + array[i].getGDP());
				System.out.println("Happiness Rank: " + array[i].getHappyRank());
			}

		}

	}

	/**
	 * This method looks for the desired country object of the user if it is sorted,
	 * use Binary Search if not, use sequential search.
	 * 
	 * @param Country[] array, holds country objects
	 * @param String    Key, input string value from user to obtain desired country
	 * 
	 */
	private static void locateCountry(Country[] array, String key) {
		System.out.println("Verifying if the array is sorted...");
		if (!checkOrder(array)) {
			System.out.println("Unsorted --> Using Sequential Search...");
			searchSequential(array, key);
		} else {
			System.out.println("Sorted --> Using Binary Search");
			binarySearch(array, key);
		}

	}

}// end Project1 class
