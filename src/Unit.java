import java.time.LocalDate;

/**
 * Represents the Abstract class of a type of a single "Unit" of the Storage Location
 * @author Sprague
 *
 */

public abstract class Unit {
	/**
	 * Customer object
	 */
	private Customer customer;
	/**
	 * Unit's size parameters
	 */
	private int unitWidth, unitLength, unitHeight;
	/**
	 * Unit's price
	 */
	private double unitPrice;
	/**
	 * Unit's start date when rented by Customer
	 */
	private LocalDate startDate;
	
	/**
	 * Constructor for Unit
	 * @param unitWidth Integer Width of the unit
	 * @param unitLength Integer Length of the unit
	 * @param unitHeight Integer Height of the unit
	 * @param locationPrice Double of the base price for the specified unit
	 */
	public Unit(int unitWidth, int unitLength, int unitHeight, double locationPrice) {
		if ( unitWidth < 0 || unitLength < 0 || unitHeight < 0 ) {
			throw new IllegalArgumentException("Invalid Unit Size");
		}
		
		if(locationPrice < 0.0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		
		this.unitWidth = unitWidth;
		this.unitLength = unitLength;
		this.unitHeight = unitHeight;
		this.unitPrice = locationPrice;
	}
	

	/**
	 * Get the Customer for the unit
	 * @return Customer assigned to unit
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get the width of the Unit
	 * @return Integer width of the unit.
	 */
	public int getUnitWidth() {
		return unitWidth;
	}
	
	/**
	 * Get the height of the Unit
	 * @return Integer height of the unit.
	 */
	public int getUnitHeight() {
		return unitHeight;
	}
	
	/**
	 * Get the length of the Unit
	 * @return Integer length of the unit.
	 */
	public int getUnitLength() {
		return unitLength;
	}
	
	/**
	 * Get the price of the unit
	 * @return Price of the unit, if rented, the rented price. If unrented, standard price
	 */
	public double getPrice() {
		return unitPrice;
	}
	/**
	 * Set the price of the price of the unit
	 * @param price Double of the price of the unit
	 */
	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		
		unitPrice = price;
	}
	
	/**
	 * Abstract method to calculate the price of the specific unit
	 * @return Double of price of the Unit after calculated
	 */
	public abstract double calculatePrice();
	
	/**
	 * Get the rental start date of the Unit
	 * @return Date of rental started.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/**
	 * Rent the unit to a customer
	 * @param customer Customer object to assign to the unit
	 */
	public void rent(Customer customer) {
		this.customer = customer;
		startDate = LocalDate.now();
	}

	/**
	 * Unrent the unit, remove all information about the customer, price and start date. 
	 */
	public void unRent() {
		this.customer = null;
		startDate = null;
	}
	
	/**
	 * Concat all the information about the Unit
	 */
	
	public String toString() {
		String str = "Unit Type: " + this.getClass().getName() 
					+ " Dimensions: " 
					+ unitWidth + "Wx" 
					+ unitLength + "Lx" 
					+ unitHeight + "H "
					+ " Price: " + unitPrice
					+ " Start Date: " + startDate
					+ " Customer: " + customer;
		
		return "\n" + str;
	}
}
