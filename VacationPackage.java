// COURSE: CSCI1620
// TERM: Fall 2020
//
// NAME: Mike Swanson, Andrew Fisher
// RESOURCES: Piazza, Canvas
package triptypes;

/**
 * This class serves as the base class of all types of vacation packages within the travel agency program.
 * @author Michael Swanson, Andrew Fisher
 *
 */
public abstract class VacationPackage 
{
	
	/**
	 * Name of the Package.
	 */
	private String nameOfPackage;
	/**
	 * Number of Days during the Vacation.
	 */
	private int numOfDays;
	/**
	 * Initializes a VacationPackage with provided values.
	 * @param name The promotional marketing name for this package.
	 * @param numDays The number of days included in this VacationPackage trip.
	 */
	public VacationPackage(String name, int numDays)
	{
		setName(name);
		setLength(numDays);
	}	
	/**
	 * Updates the VacationPackage's externally facing promotional name. 
	 * Due to travel agency policy, this name is never allowed to be empty 
	 * as it would be confusing for agents and customers interacting with the system. 
	 * Names that do not comply with this policy will be ignored and the 
	 * package will be given the name "PACKAGE NAME TBD" as a placeholder.
	 * @param name The updated name to use for this package.
	 */
	public void setName(String name)
	{
		if (name == null || name.equals("")) //method
		{
			this.nameOfPackage = "PACKAGE NAME TBD";
		}
		else
		{
			this.nameOfPackage = name;
		}
	}
	/**
	 * Updates the length of this VacationPackage. 
	 * All packages must have a minimum of one day.
	 * @param numDays numDays - The new number of days for this package.
	 */
	public void setLength(int numDays)
	{
		if (numDays > 0) //method
		{
			this.numOfDays = numDays;
		}
		else
		{
			this.numOfDays = 1;
		}
		
	}
	/**
	 * Retrieves the promotional name of this package.
	 * @return The name.
	 */
	public String getName()
	{
		return this.nameOfPackage;
	}
	/**
	 * Retrieves the number of days included in this package.
	 * @return The number of days for this trip.
	 */
	public int getNumDays()
	{
		return numOfDays;
	}
	/**
	 * This method provides the full price of a vacation package, 
	 * which is must be computed based on the specific kind of trip being booked.
	 * @return The price of a vacation package in US Dollars.
	 */
	public abstract double getPrice();
	
	/**
	 * This method provides the required upfront deposit amount for a given vacation. 
	 * This must be computed based on rules determined by specific package types, 
	 * per travel agency policies.
	 * @return The deposit amount required in US Dollars.
	 */
	public abstract double getDepositAmount();
	
	/**
	 * This method provides the remaining amount due to the travel 
	 * agent for this trip less any deposit made upfront.
	 * @return The remaining balance to pay the travel agency.
	 */
	public double getAmountDue()
	{
		return getPrice() - getDepositAmount();
	}
	/**
	 * This method provides the subtotal for a trip related to lodging expenses
	 *(ie, not including flights, meals, and incidentals). 
	 * Lodging rates are determined by specific package types.
	 * @return The lodging subtotal in US Dollars.
	 */
	public abstract double getLodgingCost();
	/**
	 *This method produces a String summary of a VacationPackage. 
	 *Strings will be prefixed with the $ symbol, 
	 *followed by trip total price rounded to two decimal places in a 8 character wide field. 
	 *Price details should be followed by two spaces and the promotional name of this trip. 
	 */
	@Override
	public String toString()
	{
		String result = String.format("$%8.2f  %s", this.getPrice(), this.getName()); 
		return result;
	}
	/**
	 *Provides a logical equality comparison for VacationPackages and any other object type.
	 */
	@Override
	public boolean equals(Object other)
	{
		boolean result = false;
		if (other instanceof VacationPackage)
		{
			VacationPackage o = (VacationPackage) other;
			if (this.getName() == o.getName()) // method
			{
				result = true;
			}
		}
		return result;
	}
}
