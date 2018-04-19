//	Temperature-controlled units add $1.00 per cubic foot of space in the unit.

//	For temperature-controlled units, temperatures can be similarly specified, 
//	in the range 45° to 70° (whole numbers). 
//	Customers who want temperatures in the 45-49° or 65-70° range pay an extra $30 a month.
/**
 * Represents the UnitTemperature class which extends the Unit class, this is a type of Temperature controlled unit in Storage Location
 * @author Sprague
 *
 */
public class UnitTemperature extends Unit{
	
	/**
	 * UnitTemperature charge per cubic feet
	 */
	private final double CHARGE_PER_CU_FT = 1.0;
	/**
	 * UnitTemperature charge for controlling the temperature in the low and high ranges 
	 */
	private final double CHARGE_HIGH_LOW_TEMP = 30.0;
	/**
	 * Location price is location base price, unitPrice is the Unit's calculated price 
	 */
	private double locationPrice, unitPrice;
	/**
	 * Variables for unit's parameters: temperature, width, length, and height
	 */
	private int temperatureLevel, unitWidth, unitLength, unitHeight;
	
	/**
	 * UnitTemperature constructor
	 * @param unitWidth Integer of the width of the UnitTemperature
	 * @param unitLength Integer of the length of the UnitTemperature
	 * @param unitHeight Integer of the height of the UnitTemperature
	 * @param locationPrice Double of the width of the base price of the location
	 */
	public UnitTemperature(int unitWidth, int unitLength, int unitHeight, double locationPrice) {
		super(unitWidth, unitLength, unitHeight, locationPrice);
		this.locationPrice = locationPrice;
		this.unitWidth = unitWidth;
		this.unitLength = unitLength;
		this.unitHeight = unitHeight;
		calculatePrice();
	}
	
	/**
	 * Get the Temperature Level of the UnitTemperature
	 * @return Integer of the Temperature Level of UnitTemperature
	 */
	public int getTemperature() {
		return temperatureLevel;
	}
	
	/**
	 * Set the temperature level of UnitTemperature
	 * @param temp Integer Temperature level for UnitTemperature
	 */
	public void setTemperatureLevel(int temp) {
		//CHECK IF TEMPERATURE IS OUT OF RANGE		
		if(temp < 45 || temp > 70) {
			throw new IllegalArgumentException("Invalid Temperature Range");
		}
		temperatureLevel = temp;
	}
	
	/**
	 * Calculates the price of the UnitTemperature based on size without temperature levels set yet
	 */
	public double calculatePrice(){
		unitPrice = locationPrice + (CHARGE_PER_CU_FT * (unitLength * unitWidth * unitHeight));
		setPrice(unitPrice);
		return unitPrice;
	}
	
	/**
	 * Calculates the UnitTemperature Price with temperature param, does not assign to specific unit
	 * @param temp Temperature the Customer needs the unit to be set
	 * @return Calculated price of the UnitTemperature based on temperature 
	 */
	public double calculatePrice(int temp){
		setTemperatureLevel(temp);
		calculatePrice();
		
		// IF TEMPERATURE IS SET IN THE EXTREME HIGH OR LOW RANGE, CHARGE EXTRA FEE
		if( temperatureLevel < 50 || temperatureLevel > 64 ){
			unitPrice += CHARGE_HIGH_LOW_TEMP;
		}
		
		setPrice(unitPrice);
		return unitPrice;
	}
	
	/**
	 * Assigns the customer object to a specific UnitTemperature, and calculates price
	 * @param customer Customer object to assign to the UnitTemperature
	 * @param temp Temperature to set the UnitTemperature control based on the Temperature parameters
	 */
	public void rent(Customer customer, int temp) {
		super.rent(customer);
		calculatePrice(temp);
	}
	
	/**
	 * Returns concat String of UnitTemperature Information
	 */
	public String toString() {
		String str = super.toString();
		double temp = getTemperature();
		if(temp != 0) {
			str += " Temperature: " + temp;
		}
		return str;
	}

}
