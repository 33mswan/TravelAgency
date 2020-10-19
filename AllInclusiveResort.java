// COURSE: CSCI1620
// TERM: Fall 2020
//
// NAME: Mike Swanson, Andrew Fisher
// RESOURCES: Piazza, Canvas

package triptypes;

/**
 * This class encapsulates details related to an all-inclusive resort vacation package. 
 * Flight information to/from the resort location can be added an additional price. Once at the resort, 
 * all-inclusive packages are billed using a flat fee, 
 * regardless of the number of included amenities.
 * @author Michael Swanson, Andrew Fisher
 */
public class AllInclusiveResort extends FlightOptionalPackage 
{
	/**
	 * Resort Name.
	 */
	private String resortName;
	/**
	 * Guests per room counter.
	 */
	private int peoplePerRoom;
	/**
	 * Price per night for the room.
	 */
	private double priceANight;
	/**
	 * List of amenities.
	 */
	private String[] amenitiesLists;

	/**
	 * Creates a new AllInclusiveResort package.
	 * @param name The promotional name for this package.
	 * @param numDays The number of days included in this vacation package.
	 * @param resort The name of the resort.
	 * @param guestsPerRoom The number of guests allowed per room at the included price.
	 * @param pricePerNight The price of the stay per night.
	 * @param amenitiesList  A list of amenities available for free at the resort.
	 */
	public AllInclusiveResort(String name, int numDays, String resort,
			int guestsPerRoom, double pricePerNight, String[] amenitiesList)
	{
		super(name, numDays);
		resortName = resort;
		this.peoplePerRoom = guestsPerRoom;
		this.priceANight = pricePerNight;
		this.amenitiesLists = amenitiesList;
	}
	/**
	 * Retrieves a single String containing all of the available amenities at the resort. 
	 * Individual amenities should be separated by a single comma and space character, 
	 * with no such separator at the end of the string.
	 * @return The amenities list.
	 */
	public String getAmenities()
	{
		String amenList = "";
		for (int amen = 0; amen < amenitiesLists.length; amen++)
		{
			amenList = amenList + amenListHelper(amen);
		}
		return amenList;
	}
	private String amenListHelper(int pos)
	{
		if (pos < amenitiesLists.length - 1)
		{
			return String.format("%s, ", amenitiesLists[pos]);
		}
		else
		{
			return String.format("%s", amenitiesLists[pos]);
		}
	}
	/**
	 * Retrieves the number of guests allowed per room at the specified rate.
	 * @return The number of guests per room.
	 */
	public int getGuestsPerRoom()
	{
		return peoplePerRoom;
	}
	/**
	 *The full price for this all-inclusive package, including any optional flight pricing.
	 *@return price of a vacation package in US Dollars.
	 */
	public double getPrice()
	{
		return getLodgingCost() + getFlightCosts();
	}
	/**
	 *The required deposit amount to be made at the time of booking. 
	 *All upfront flight-related costs and 50% of the total lodging costs for this trip 
	 *must be paid as a deposit.
	 *@return The deposit amount required in US Dollars.
	 */
	public double getDepositAmount()
	{
		return (getLodgingCost() / 2) + getFlightCosts();
	}
	/**
	 *A formatted summary string with details about this all-inclusive resort package. 
	 *The string will contain information as described by FlightOptionalPackage followed 
	 *by details about the resort and the number of people included at the specified price. 
	 *The second line should be prefixed with 11 blank spaces for alignment below the trip name.
	 *@return The formatted string summary.
	 */
	public String toString()
	{
		return String.format("%s\n           An all-inclusive stay at %s for %d people!",
				super.toString(), resortName, peoplePerRoom);
	}
	/**
	 *Retrieves the subtotal for lodging associated with this all inclusive resort 
	 *stay based on the number of nights in the package and the base price.
	 *@return The lodging subtotal in US Dollars.
	 */
	public double getLodgingCost()
	{
		return priceANight * (super.getNumDays() - 1);
	}
}
