/**
 * Represents the UnitHumidity class extending Unit. Humidity controlled units must have a humidity range
 * @author Sprague
 *
 */

public class UnitHumidity extends Unit{
	/**
	 * Customers specify the humidity level, from 20 to 60% (whole numbers).
	 */
	private int humidityLevel;
	/**
	 * Price of the unit and location base price
	 */
	private double unitPrice, locationPrice;
	/**
	 * Humidity-controlled units add $5.00 per square foot of floor space in the unit
	 */
	private final double CHARGE_PER_SQ_FT = 5.0;
	/**
	 * Customers who want humidity in the 20-29% range pay an extra $20 a month.
	 */
	private final double CHARGE_LOW_HUMIDITY = 20.0;
	
	/**
	 * UnitHumidity constructor 
	 * @param width Integer of width of the UnitHumidity
	 * @param length Integer of length of the UnitHumidity
	 * @param height	Integer of height of the UnitHumidity
	 * @param locationPrice double of StorageLocation's base price
	 */
	public UnitHumidity(int width, int length, int height, double locationPrice) {
		super(width, length, height, locationPrice);
		checkValidPrice(locationPrice);
		
		this.locationPrice = locationPrice;
		calculatePrice();
	}
	
	/**
	 * Helper function to check valid price withing acceptable range
	 * @param price double of price to check
	 */
	private void checkValidPrice(double price) {
		if(price < 0.0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
	}
	
	/**
	 * Get the humidity level of the UnitHumidity
	 * @return integer of humidity level
	 */
	public int getHumidityLevel() {
		return humidityLevel;
	}
	
	/**
	 * Set the humidity level of the UnitHumidity
	 * @param humidityLevel integer of the humidity for UnitHumidity
	 */
	private void setHumidityLevel(int humidityLevel) {
		if(humidityLevel < 20 || humidityLevel > 60) {
			throw new IllegalArgumentException("Invalid Humidity Level");
		}
		
		this.humidityLevel = humidityLevel;
	}
	
	/**
	 * Calculate the price of the unit based on unit size only, does not assign customer or price for humidity level
	 */
	public double calculatePrice(){
		int len = getUnitLength();
		int width = getUnitWidth();
		
		checkValidPrice(locationPrice);
		unitPrice = (CHARGE_PER_SQ_FT * len * width) + locationPrice;
		setPrice(unitPrice);
		return unitPrice;
	}
	
	/**
	 * Calculate the price with the humidity level range
	 * @param humidityLevel Integer of humidity level
	 * @return double of the Price of the unit
	 */
	public double calculatePrice(int humidityLevel){
		setHumidityLevel(humidityLevel);
		calculatePrice();
		
		if(humidityLevel < 30) {
			unitPrice += CHARGE_LOW_HUMIDITY;
		}
		
		setPrice(unitPrice);
		return  unitPrice;
	}
	
	/**
	 * Assigns the Customer to the UnitHumidity with Humidity level pricing calculated
	 * @param customer Customer object
	 * @param humidityLevel Integer of humidity level
	 */
	public void rent(Customer customer, int humidityLevel) {
		super.rent(customer);
	
		calculatePrice(humidityLevel);
	}
	
	public String toString() {
		String str = super.toString();
		double humidity = getHumidityLevel();
		if(humidity != 0) {
			str += " Humidity: " + humidity;
		}
		return str;
	}
}
