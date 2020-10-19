// COURSE: CSCI1620
// TERM: Fall 2020
//
// NAME: Mike Swanson, Andrew Fisher
// RESOURCES: Piazza, Canvas

package triptypes;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * This class represents a single flight within the travel agency system.
 * @author Michael Swanson, Andrew Fisher
 *
 */
public class Flight 
{
	/**
	 * Code for airline noted in two characters.
	 */
	private String airLineCode;
	/**
	 * Number of flight.
	 */
	private int flightNumber;
	/**
	 * Departure location.
	 */
	private String departure;
	/**
	 * Arrival location.
	 */
	private String destination;
	/**
	 * Departure time.
	 */
	private Calendar leaveTime;
	/**
	 * Arrival time.
	 */
	private Calendar arrivalTime;
	/**
	 * Price of flight.
	 */
	private double priceOfFlight;
	/**
	 * Creates a new flight leg in the system.
	 * @param airline The two letter airline code (e.g, "DL", "AA")
	 * @param flightNum The unique flight number on a given day
	 * @param from The three letter airport code for the departure airport (e.g, "OMA")
	 * @param to The three letter airport code for the arrival airport (e.g., "CDG")
	 * @param leavesAt The departure time
	 * @param arrives The arrival time
	 * @param price The price for this flight in US Dollars.
	 */
	public Flight(String airline, int flightNum, String from, String to,  
			Calendar leavesAt, Calendar arrives, double price)
	{
		airLineCode = airline;
		flightNumber = flightNum;
		departure = from;
		leaveTime = leavesAt;
		arrivalTime = arrives;
		priceOfFlight = price;
		destination = to;
	}
	/**
	 * Retrieves the price of this flight.
	 * @return The price in US Dollars.
	 */
	public double getPrice()
	{
		return priceOfFlight; 
	}
	/**
	 *Retrieves a formatted string summarizing this Flight.
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm MM-dd-yyyy");
		return String.format("%s%4d Departs: %s at %s; Arrives %s at %s",
				airLineCode, flightNumber, departure,
					formatter.format(leaveTime.getTime()), 
						destination, formatter.format(arrivalTime.getTime()));
	}
}
