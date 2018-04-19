/**
 * UnitStandard class of a type of Unit in StorageUnit extending Unit
 * @author Sprague
 *
 */
public class UnitStandard extends Unit{
	/**
	 * Location price is base price of the Storage Location
	 */
	private double locationPrice;
	/**
	 * Standard units add an additional flat rate of $75 on top of the base price.
	 */
	private final double FLAT_RATE = 75.0;
	
	/**
	 * UnitStandard constructor
	 * @param unitWidth Integer of width of UnitStandard
	 * @param unitLength Integer of length of UnitStandard
	 * @param unitHeight Integer of height of UnitStandard
	 * @param locationPrice Double of base price of StorageLocation
	 */
	public UnitStandard(int unitWidth, int unitLength, int unitHeight, double locationPrice) {
		super(unitWidth, unitLength, unitHeight, locationPrice);
		this.locationPrice = locationPrice;
		calculatePrice();
	}
	
	/**
	 * calculate the price of Standard unit
	 */
	public double calculatePrice(){
		if (locationPrice < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		double price = locationPrice + FLAT_RATE;
		setPrice(price);
		return price;
	}
	
	/**
	 * Assign the customer to a standard unit and calculate the price
	 */
	public void rent(Customer customer) {
		super.rent(customer);
		
		calculatePrice();
	}
	
}
