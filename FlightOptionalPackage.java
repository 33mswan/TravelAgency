// COURSE: CSCI1620
// TERM: Fall 2020
//
// NAME: Mike Swanson, Andrew Fisher
// RESOURCES: Piazza, Canvas

package triptypes;

/**
 * This class encapsulates information about travel packages that can include optional flight arrangements. 
 * Trips that have this format price flight information as an additional charge to a base 
 * package price captured in a concrete subclass of FlightOptionalPackage.
 * @author Michael Swanson, Andrew Fisher
 *
 */
public abstract class FlightOptionalPackage extends VacationPackage
{
	
	/**
	 * Magic number solver.
	 */
	private final int flightArrayNum = 12;
	/**
	 * Flight itinerary array.
	 */
	private Flight[] itinerary = new Flight[flightArrayNum];
	/**
	 * Counter for number of flights.
	 */
	private int numOfFlights = 0; 
	/**
	 * Initializes details for a newly created FlightOptionalPackage. 
	 * Upon creation, this package will contain no flight bookings. 
	 * However, flight legs can be added at a later point.
	 * @param name The promotional name of this package.
	 * @param numDays The number of days this travel package covers.
	 */
	public FlightOptionalPackage(String name, int numDays)
	{
		super(name, numDays);
	}
	/**
	 * Adds a single one-way flight to this package. 
	 * Round trips and layovers are handled by multiple calls to addFlightLeg with one call for each flight. 
	 * Flights must be added in chronological order, with the soonest flight added first. 
	 * A maximum of up to 12 flights can be stored within a FlightOptionalPackage.
	 * @param details A valid flight object to append to this itinerary. 
	 * Invalid values (ie, null) or flights in excess of the 12 maximum will be ignored 
	 * and will not impact this FlightOptionalPackage object.
	 */
	public void addFlightLeg(Flight details)
	{
		if (numOfFlights < flightArrayNum && details != null)
		{
			itinerary[numOfFlights] = details;
			numOfFlights++;
		}
	}
	/**
	 * A predicate method for identifying whether a concrete object has at least one flight attached to it.
	 * @return true when at least one flight has been added, false otherwise.
	 */
	public boolean hasFlights()
	{
		return numOfFlights > 0;
	}
	/**
	 * Retrieves the current itinerary for this travel package. 
	 * Flights are positioned in the returned array in the order 
	 * in which they were added to this FlightOptionalPackage.
	 * @return The current itinerary array of Flight objects. null when no flights have been added yet.
	 */
	public Flight[] getFlightItinerary()
	{
		if (numOfFlights <= 0)
		{
			return null;
		}
		return itinerary;
	}
	/**
	 * The total cost of all legs in this FlightOptionalPackage.
	 * @return The sum of all individual flight costs in this package.
	 */
	public double getFlightCosts()
	{
		double flightCost = 0;
		for (int flight = 0; flight < numOfFlights; flight++) 
		{
			flightCost += itinerary[flight].getPrice(); 
		}
		return flightCost;
	}
	/**
	 * the number of Flights in the array.
	 * @return An integer containing the number of flights
	 */
	public int getNumOfFlights()
	{
		return numOfFlights;
	}
	/**
	 *Retrieves a formatted string summary of this travel package.
	 *This string will be formatted exactly as prescribed by the TravelPackage class, 
	 *with a postfix indicating whether flight information is included or not.
	 */
	@Override
	public String toString()
	{
		if (numOfFlights > 0)
		{
			return String.format("%s (Flight Included)", super.toString());
		}
		return String.format("%s (Flight Not Included)", super.toString());
	}

}
