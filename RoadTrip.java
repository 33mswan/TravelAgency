// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Michael Swanson, Andrew Fisher
// RESOURCES: Canvas, Piazza, CSLC

package triptypes;

/**
 * This class represents a RoadTrip vacation that includes a rental car, overnight lodging, and fuel cost estimation.
 * @author Michael Swanson, Andrew Fisher
 *
 */
public class RoadTrip extends VacationPackage 
{
	/**
	 * Base lodging cost.
	 */
	private final double baseLODGINGCOST = 35.2; 
	/**
	 * Maximum car cost for road trip.
	 */
	private final double maxCARCOST = 150.00;
	/**
	 * The default price of fuel.
	 */
	private final double defaultFuelPrice = 2.50;
	/**
	 * The number of stops.
	 */
	private String[] numStops;
	/**
	 * Hotel star rating.
	 */
	private int numHotelStars;
	/**
	 * Number of miles to travel.
	 */
	private int numMiles;
	/**
	 * Total number of persons traveling.
	 */
	private int totalPersons;
	/**
	 * Fuel cost per gallon.
	 */
	private double fuelCostPerGallon;
	/**
	 * Magic number solver.
	 */
	private final int oneToTwoPeople = 45;
	/**
	 * Magic number solver.
	 */
	private final int threeToFourPeople = 32;
	/**
	 * Magic number solver.
	 */
	private final int fiveToSixPeople = 28;
	/**
	 * Magic number solver.
	 */
	private final int sevenToEightPeople = 22;
	/**
	 * Magic number solver.
	 */
	private final int nineToMany = 15;
	/**
	 * Magic number solver.
	 */
	private final double carForTwo = 36.75;
	/**
	 * Magic number solver.
	 */
	private final double carForFour = 50.13;
	/**
	 * Magic number solver.
	 */
	private final double carForSix = 60.25;
	/**
	 * Magic number solver.
	 */
	private final double carForEight = 70.50;
	/**
	 * Magic number solver.
	 */
	private final double bus = 150;
	/**
	 * Creates a newly initialized RoadTrip object using the parameter data.
	 * @param name The promotional name to use for this RoadTrip in the travel agency system.
	 * @param numDays The number of days required for this RoadTrip.
	 * @param stops A list of destinations that will be visited along the way on this RoadTrip.
	 * @param fuelCost The estimated cost of fuel in US Dollars per Gallon based on current rates.
	 * @param miles The total number of miles for this RoadTrip, assuming people follow the intended route.
	 * @param maxPersons The number of people for whom this trip package will be budgeted.
	 * @param hotelStars The quality level of the hotels used during the RoadTrip, 
	 * ranging from 1..5 stars, inclusive. 
	 * Star values outside this range will be adjusted to the closest valid value.
	 */
	public RoadTrip(String name, int numDays, String[] stops, double fuelCost,
			int miles, int maxPersons, int hotelStars)
	{
		super(name, numDays);
		numStops = new String[stops.length];
		numStops = stops;
		hotelStarHelper(hotelStars);
		setFuelPrice(fuelCost);
		numMiles = miles;
		totalPersons = maxPersons;		
	}
	private void hotelStarHelper(int numStar)
	{
		if (numStar < 1) 
		{
			numHotelStars = 1;
		}
		else if (numStar > 5)
		{
			numHotelStars = 5;
		}
		else 
		{
			numHotelStars = numStar;
		}
	}
	/**
	 * Retrieves the hotel quality level attached to this RoadTrip package.
	 * @return The number of stars for hotel stays.
	 */
	public int getHotelStars()
	{
		return numHotelStars;
	}
	/**
	 * Provides the full price of this RoadTrip object. 
	 * RoadTrip prices are computed based on the total rental car, lodging, 
	 * and fuel estimated costs.
	 */
	@Override
	public double getPrice()
	{
		double totalPrice = getLodgingCost() + getCarCost() + getEstimatedFuelCost();
		return totalPrice;
	}
	/**
	 * Provides the required deposit amount for this RoadTrip object. 
	 * The required deposit for a Road trip includes the full lodging cost plus the full rental car cost.
	 */
	@Override
	public double getDepositAmount()
	{
		double depositAmount = getLodgingCost() + getCarCost();
		return depositAmount;
	}
	/**
	 *All RoadTrips must be fully paid in advance, with the exception of fuel costs. 
	 *Fuel costs are paid to the gas station, and not the travel agent. 
	 *Thus, the balance due on RoadTrips is always 0.
	 */
	@Override
	public double getAmountDue()
	{
		return 0.0;
	}
	/**
	 * Provides the total lodging cost for a RoadTrip object. 
	 * Lodging is computed based on the length of the vacation, 
	 * the quality of the hotel (in stars), the number of rooms needed for the party 
	 * and a base charge of $35.20 per room per night. 
	 * Lodging costs assume a maximum 2 person occupancy per room.
	 * @return The lodging subtotal in US Dollars.
	 */
	public double getLodgingCost()
	{
		double lodgingCost = baseLODGINGCOST * getHotelStars()
				* (super.getNumDays() - 1) * ((totalPersons % 2) + (totalPersons / 2));
		return lodgingCost;
	}
	/**
	 * Provides the total cost for the rental car based on the trip duration and the size of car needed. 
	 * Rental cars are billed based on full days, with no partial day rentals allowed. 
	 * Further, the travel agency uses a standard daily rental car charge based on the number
	 *  of occupants riding along:
	 * @return The number of intermediate destinations.
	 */
	public double getCarCost()
	{
		double carCost = maxCARCOST;
		switch (totalPersons)
		{
			case 1: case 2:
				carCost = carForTwo;
				break;
			case 3: case 4:
				carCost = carForFour; // rename
				break;
			case 5: case 6:
				carCost = carForSix;
				break;
			case 7: case 8:
				carCost = carForEight;
				break;
			default:
				carCost = bus;
				break;
		}
		return carCost * super.getNumDays();
	}
	/**
	 * Retrieves the number of stops along the route for this RoadTrip.
	 * @return The number of intermediate destinations.
	 */
	public int getNumStops()
	{
		return numStops.length;
	}
	/**
	 * Retrieves the number of people included for budget calculations by this RoadTrip.
	 * @return The number of persons.
	 */
	public int getNumPersons()
	{
		return totalPersons;
	}
	/**
	 * Updates the number of people to be used for budgeting this RoadTrip within the travel agency.
	 * @param maxPeople The new number of people to use in calculations.
	 */
	public void setPersons(int maxPeople)
	{
		totalPersons = maxPeople;
	}
	/**
	 * Updates the cost of fuel in US dollars per gallon. 
	 * This value is used for projecting out costs for this RoadTrip. 
	 * Prices must be positive values, and a default assumption of $2.50 per 
	 * gallon will be used if an invalid price is specified.
	 * @param pricePerGallon The new price to use for cost projections.
	 */
	public void setFuelPrice(double pricePerGallon)
	{
		if (pricePerGallon > 0)
		{
			fuelCostPerGallon = pricePerGallon;
		}
		else
		{
			fuelCostPerGallon = defaultFuelPrice;
		}
	}
	/**
	 * Retrieves the current fuel price used for cost projections.
	 * @return The fuel price in US dollars per gallon.
	 */
	public double getFuelPrice()
	{
		return fuelCostPerGallon;
	}
	/**
	 * Provides a projection of the total fuel cost
	 * for this trip based on the total number of miles to be traveled, 
	 * the fuel efficiency of the rental car, and the cost of fuel. 
	 * Standard rental cars used have decreasing fuel efficiency as the size gets bigger. 
	 * Thus, efficiency is a function of passenger count. We assume the following projections:
	 * @return The projected fuel cost in US dollars.
	 */
	public double getEstimatedFuelCost()
	{
		double milesPerGallon;
		switch (totalPersons)
		{
			case 1: case 2:
				milesPerGallon = oneToTwoPeople;
				break;
			case 3: case 4:
				milesPerGallon = threeToFourPeople; //rename
				break;
			case 5: case 6:
				milesPerGallon = fiveToSixPeople;
				break;
			case 7: case 8:
				milesPerGallon = sevenToEightPeople;
				break;
			default:
				milesPerGallon = nineToMany;
				break;
		}
		double estimatedFuelCost = (numMiles / milesPerGallon) * getFuelPrice();
		return estimatedFuelCost;
	}
	/**
	 * Retrieves the list of stops on this RoadTip as a single string with values 
	 * separated by a comma and a single space. 
	 * The last stop has no punctuation after it.
	 * @return The list of stops.
	 */
	public String getStops()
	{
		String list = "";
		for (int s = 0; s < numStops.length; s++)
		{
			if (s < numStops.length - 1) 
			{	
				list = list + String.format("%s, ", numStops[s]); // do
			}
			else
			{
				list = list + String.format("%s", numStops[s]);
			}
		}
		return list;
	}
	/**
	 * Provides a string summary of this RoadTrip.
	 */
	@Override
	public String toString()
	{
		return String.format("%s\n           A road trip with stops at %s", super.toString(), getStops());
	}
}
