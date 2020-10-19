// COURSE: CSCI1620
// TERM: Fall 2020
//
// NAME: Mike Swanson, Andrew Fisher
// RESOURCES: Piazza, Canvas
package travelgui;
import triptypes.AllInclusiveResort;
import triptypes.Cruise;
import triptypes.RoadTrip;
import triptypes.FlightOptionalPackage;
import triptypes.VacationPackage;

/**
 * This class serves as a collection to store, filter, 
 * and explore VacationPackages within the trip search user interface. 
 * Up to 25 VacationPackages can be stored in a single collection.
 * @author Michael Swanson, Andrew Fisher
 *
 */
public class VacationPackageCollection 
{
	/**
	 * Total number of possible VacationPackages. 
	 */
	private final int maxArray = 25;
	/**
	 * an array of VacationPackages.
	 */
	private VacationPackage[] vacationCollection;
	/**
	 * The number of Items in the array.
	 */
	private int numVacationPackages = 0;
	/**
	 * Creates a new empty VacationPackageCollection.
	 */
	public VacationPackageCollection()
	{
		vacationCollection = new VacationPackage[maxArray];
	}
	/**
	 * Retrieves the number of trip packages that have been added to this collection for purchase.
	 * @return The total number of available packages.
	 */
	public int getNumTrips()
	{
		return numVacationPackages;
	}
	/**
	 * Retrieves an array of all available packages from the collection. 
	 * Valid packages are guaranteed to be stored contiguously in the left most 
	 * array cells. Any empty cells will appear to the right of the last valid 
	 * VacationPackage.
	 * @return The list of available packages, or null if no packages exist.
	 */
	public VacationPackage[] getAllVacations()
	{
		return vacationCollection;
	}
	/**
	 * Adds a single trip package to the collection at the next available position. 
	 * If adding this trip would result in more than 25 total packages, 
	 * it will not be added to the collection.
	 * @param trip The trip to add to the collection.
	 */
	public void addVacation(VacationPackage trip)
	{
		vacationCollection[numVacationPackages] = trip;
		numVacationPackages++;
	}
	/**
	 * Retrieves a filtered subcollection of trips corresponding to a specific subtype. 
	 * Should no trips of a specified type be present in this 
	 * VacationPackageCollection an empty collection containing zero packages will be returned.
	 * @param selection An integer signaling what type of packages to extract. 1 represents RoadTrips, 
	 * 2 represents Cruises, and 3 represents All-Inclusive Resort packages.
	 * @return The filtered subcollection of packages, or an empty collection 
	 * if no matching packages exist in this collection.
	 */
	public VacationPackageCollection filterVacationsFor(int selection)
	{
		VacationPackageCollection out = new VacationPackageCollection();
		switch (selection)
		{
			case 1:
				filterVacationsForRoadTrip(out);
				break;
			case 2:
				filterVacationsForCruise(out);
				break;
			case 3:
				filterVacationsForAllInclusive(out);
				break;
			default:
				break;
		}
		return out;
	}
	/**
	 * filters for AllInclusive Resorts.
	 * @param result
	 */
	private void filterVacationsForAllInclusive(VacationPackageCollection result)
	{
		for (int pos = 0; pos < vacationCollection.length; pos++)
		{
			if (getAllVacations()[pos] instanceof AllInclusiveResort)
			{
				result.addVacation(getAllVacations()[pos]);
			}
		}
	}
	/**
	 * Filters for RoadTrips.
	 * @param result
	 */
	private void filterVacationsForRoadTrip(VacationPackageCollection result)
	{
		for (int pos = 0; pos < vacationCollection.length; pos++)
		{
			if (getAllVacations()[pos] instanceof RoadTrip)
			{
				result.addVacation(getAllVacations()[pos]);
			}
		}
	}
	/**
	 * Filters for Cruises
	 * @param result
	 */
	private void filterVacationsForCruise(VacationPackageCollection result)
	{
		for (int pos = 0; pos < vacationCollection.length; pos++)
		{
			if (getAllVacations()[pos] instanceof Cruise)
			{
				result.addVacation(getAllVacations()[pos]);
			}
		}
	}
	/**
	 * Produces a summary of flight information inside a VacationPackage for detail display elsewhere. 
	 * In addition to properly extracting flight details when the given index corresponds to a 
	 * FlightOptionalPackage with one or more flight legs, this method will also 
	 * dectect various error conditions. In erroneous situations, one of the following 
	 * strings will be produced:
	 * @param index The index position in the collection from which to extract flight information.
	 * @return The formatted flight details, with one Flight per line and each Flight displayed 
	 * as detailed in Flight.toString.
	 */
	public String getFlightDetails(int index) // absolutle
	{
		String result = ""; 
		if (index >= 0)
		{
			result = FlightsDetailsHelper(index);
		}
		else
		{
			result = "ERROR: Index is out of range!";
		}
		return result;
	}
	
	/**
	 * Provides 0-based indexed access to the VacationPackageCollection.
	 * @param index The index position whose VacationPackage should be returned.
	 * @return The selected VacationPackage when index is valid. The method will return null otherwise.
	 */
	public VacationPackage getItemAt(int index) 
	{
		if (index < numVacationPackages && index >= 0)
		{
			return vacationCollection[index];
		}
		return null;
	}
	/**
	 * Produces a stable sort of the contents of this VacationPackageCollection,
	 *  with the sort order determined by an externally specified criteria. 
	 * When byPrice is true, the method will sort all available packages in ascending order by total package price.
	 * @param byPrice A flag which sets the sort criteria as described above.
	 */
	public void sortCollection(boolean byPrice)
	{
		if (byPrice)
		{
			insertionSortByPrice(vacationCollection);
		}
		else
		{
			insertionSortName(vacationCollection);
		}
	}
	/**
	 * Sorting the Packages by Price.
	 * @param data The array of VacationPackages.
	 */
	public void insertionSortByPrice(VacationPackage[] data) 
	{
		VacationPackage insert;
		for (int next = 1; next < numVacationPackages; next++)
		{
			insert = data[next];
			int moveItem = next;
			while (moveItem > 0 && data[moveItem - 1].getPrice() > insert.getPrice())
			{
				VacationPackage temp = data[moveItem];
				data[moveItem] = data[moveItem - 1];
				data[moveItem - 1] = temp;	
				moveItem--;
			} 
			data[moveItem] = insert;
		}
	}
	/**
	 *  Sorting the Packages by name.
	 * @param data The array of VacationPackages.
	 */
	public void insertionSortName(VacationPackage[] data) 
	{
		VacationPackage insert;
		for (int next = 1; next < numVacationPackages; next++)
		{
			insert = data[next];
			int moveItem = next;
			while (moveItem > 0 && data[moveItem - 1].getName().compareTo(insert.getName()) > 0)
			{
				VacationPackage temp = data[moveItem];
				data[moveItem] = data[moveItem - 1];
				data[moveItem - 1] = temp;	
				moveItem--;
			} 
			data[moveItem] = insert;
		}
	}
	/**
	 * Helps gets the flight details.
	 * @param indexIn
	 * @return
	 */
	private String FlightsDetailsHelper(int indexIn)
	{
		String result = "";
		if (getAllVacations()[indexIn] instanceof FlightOptionalPackage)
		{
			result = getFlights(indexIn);
		}
		else if (getAllVacations()[indexIn] instanceof RoadTrip && indexIn >= 0)
		{
			result = "ERROR: No flights are allowed for this type of trip!";
		}
		else if (indexIn > numVacationPackages - 1)
		{
			result = "ERROR: Index is out of range!";
		}
		return result;
	}
	/**
	 * Helps get the Flight details
	 * @param indexIn
	 * @return
	 */
	private String getFlights(int indexIn)
	{
		String result = "";
			boolean hasFlights = ((FlightOptionalPackage) 
					getAllVacations()[indexIn]).hasFlights();
			if (hasFlights)
			{
				result = getFlightInfo(indexIn);
			}
			else 
			{
				result = "ERROR: The selected trip has no flight information.";
			}	
		return result;
	}
	/**
	 * Helps get the Flight details.
	 * @param indexIn
	 * @return
	 */
	private String getFlightInfo(int indexIn)
	{
		int itinerarylength = ((FlightOptionalPackage) 
				getAllVacations()[indexIn]).getNumOfFlights();
		String result = "";
		for (int pos = 0; pos < itinerarylength; pos++)
		{
			result = result + String.format("%s\n",
				((FlightOptionalPackage) 
				getAllVacations()[indexIn]).getFlightItinerary()[pos]);
		}
		return result;
	}
}
