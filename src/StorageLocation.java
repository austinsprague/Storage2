/**
 * Represents a storage location facility that holds storage units and rents to customers.
 * @author Sprague
 *
 */
public class StorageLocation {
	
	/**
	 * String of designation 
	 */
	private String designation;
	/**
	 * 2D array of all units at StorageLocation
	 */
	private Unit[][] units;
	/**
	 * Array of Customer's at StorageLocation
	 */
	private Customer[] customers;
	/**
	 * Integer count of the Customers at StorageLocation
	 */
	private int numOfCustomers;
	/**
	 * Double of base price of the StorageLocation
	 */
	private double basePrice;
	
	/**
	 * Constructor for Storage Location
	 * @param designation The String must fit the specified pattern format. Two upper-case letters indicating the state, followed by two digits representing the location number within the state, followed by the city name.i.e. “WA23Issaquah” and “OR02Ashland”.
	 */
	
	/**
	 * StorageLocation constructor
	 * @param designation String of specified format of designation
	 * @param basePrice Double of base price of StorageLocation
	 */
	public StorageLocation(String designation, double basePrice) {
		if(!designation.matches("[A-Z]{2}[0-9]{2}[A-Z][A-Za-z]+")) { //IF DESIGNATION DOES NOT HAVE TWO LETTERS, followed by two numbers and rest are all letters
			 throw new IllegalArgumentException(
					 "Designation doesn't match required format"
					 );	
		}
		
		this.designation = designation;
		this.basePrice = basePrice;
		
		numOfCustomers = 0;
		units = new Unit[12][];
		customers = new Customer[100];
		
		
		for(int row =0; row < units.length; row++) {
			
			// SET ATTRIBUTES FOR STANDARD UNITS
			if (row <= 6 ) {
				int stdWidth = 8;
				int stdHeight = 10;
				int stdLen = 9; 
				int stdNumOfUnitsPerRow = 10;
				
				units[row] = new UnitStandard[stdNumOfUnitsPerRow];
				
				for(int col = 0; col<units[row].length; col++) {
					Unit stdUnit = new UnitStandard(stdWidth, stdLen, stdHeight, basePrice);
					units[row][col]= stdUnit;
				}
			}
			
			// SET ATTRIBUTES FOR HUMIDITY CONTROLLED UNITS
			if (row >= 7  && row <= 9  ) {
				int humWidth = 5;
				int humHeight = 10;
				int humLen = 10; 
				int humNumOfUnitsPerRow = 8;
				
				units[row] = new UnitHumidity[humNumOfUnitsPerRow];
				
				for(int col = 0; col<units[row].length; col++) {
					UnitHumidity humUnit = new UnitHumidity( humWidth, humLen, humHeight, basePrice);
					units[row][col]= humUnit;
					
				}
			}
			
			// SET ATTRIBUTES FOR TEMPERATURE CONTROLLED UNITS
			if (row >= 10 ) {
				int tempWidth = 5;
				int tempHeight = 10;
				int tempLength = 10; 
				int tempNumOfUnitsPerRow = 6;
				
				units[row] = new UnitTemperature[tempNumOfUnitsPerRow];
				
				for(int col = 0; col<units[row].length; col++) {
					UnitTemperature tempUnit = new UnitTemperature( tempWidth, tempLength, tempHeight, basePrice);
					units[row][col]= tempUnit;
				}
			}
		}
	}
	
	/**
	 * Get the StorageLocation base price
	 * @return Double of base price of storage location
	 */
	public double getBasePrice() {
		return basePrice;
	}
	
	/**
	 * Get the Storage Location designation.
	 * @return The designation String of Storage Location.
	 */
	public String getDesignation(){
		return designation;
	}
	
	/**
	 * Retrieves the unit at a specified row and column.
	 * @param row The specified row.
	 * @param col The specified column.
	 * @return The unit at the specified row and column.
	 */
	public Unit getUnit(int row, int col) {
		return units[row][col];
	}
	
	/**
	 * Adds a customer to the StorageLocation.
	 * @param customer The Customer object to be added.
	 */
	public void addCustomer(Customer customer) {
		customers[numOfCustomers] = customer;
		numOfCustomers ++;
	}
	
	/**
	 * Get the Customer by index of Customer Array
	 * @param index Integer index of the Customer array 
	 * @return Customer Object
	 */
	public Customer getCustomer(int index) {
		return customers[index];
	}
	
	/**
	 * Get the number of Customers at the Storage Location
	 * @return Integer number of customers
	 */
	public int getCustomerCount() {
		return numOfCustomers;
	}
	
	/**
	 * Get all the units that are rented by specified Customer.
	 * @param customer Customer Object that needs the  
	 * @return Array of Units that are rented by Customer.
	 */
	public Unit[] getUnitsForCustomer(Customer customer) {
		int numOfUnits = 0;

		//		COUNT IF CUSTOMER MATCHES THE UNIT, THEN COUNT
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				if (cstmr == customer) {
					numOfUnits++;
				}
			}
		}
		
		//		Iterate through the units array again, check if customer matches unit, and add the unit to a new array
		Unit[] customerUnits; 
		customerUnits = new Unit[numOfUnits];
		int unitIndex = 0;
		
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				if (cstmr == customer && cstmr != null) {
					customerUnits[unitIndex] = units[row][col];
					unitIndex++;
				}
			}
		}
		return customerUnits;
	}
	
	/**
	 * Get an array of all the empty units for the Storage Location
	 * @return Array of Units that are empty.
	 */
	public Unit[] getEmptyUnits() {
		return getUnitsForCustomer(null);
	}
	
	/**
	 * Get all the empty units that are by the specified Unit Type
	 * @param unitType Storage unit type
	 * @return Array of Units by specified Unit Type. 
	 */
	
	public Unit[] getEmptyUnits(String unitType) {
		int numOfUnits = 0;

		//		COUNT IF CUSTOMER EXISTS && IF UNIT TYPE MATCHES
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				String unitClass= units[row][col].getClass().getName();
				if (cstmr == null && unitClass == unitType) {
					numOfUnits++;
				}
			}
		}
		
		//		Iterate through the units array again, check if customer is null and matches unit type, and add the unit to a new array
		Unit[] customerUnits; 
		customerUnits = new Unit[numOfUnits];
		int unitIndex = 0;
		
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				String unitClass= units[row][col].getClass().getName();
				
				if (cstmr == null && unitClass == unitType) {
					customerUnits[unitIndex] = units[row][col];
					unitIndex++;
				}
			}
		}
		return customerUnits;
	}
	
	
	/**
	 * CHANGES: multi-unit renters get a 5% discount on their monthly rent. This doesn’t change their rental price; it only changes the amount they get charged at billing time. Each discounted charge is rounded to the nearest penny.
	 * Because of these pricing changes, no rental lock-in prices are offered; standard prices are the prices.
	 * */
	
	/**
	 * Charge all customers at Storage Location by the Unit they are renting. 
	 * Customers get 5% discount if renting multiple
	 */
	public void chargeCustomer() {
		
		for( int custIdx = 0; custIdx < numOfCustomers; custIdx++ ) {
			Customer cstmr = customers[custIdx];
			Unit[] custUnits = getUnitsForCustomer(cstmr);
			
			double chargeAmt = 0;
			
			for ( int unitIdx = 0; unitIdx < custUnits.length; unitIdx ++ ) {
				chargeAmt += custUnits[unitIdx].getPrice();
			}
			
			if ( custUnits.length > 1 ) {
				chargeAmt *= 0.95;
				chargeAmt = Math.round(chargeAmt * 100.0) / 100.0;
			}
			
			cstmr.chargeAccountBalance(chargeAmt);
		}
	}
	
	/**
	 * Display the map of the Storage Location indicating rented units and position formatted
	 * @return String of formatted Storage Location units 
	 */
	public String unitMap() {
		String str = "--------------------------------------------------\n" 
					+ "         Unit Map for Location "
					+ designation + "\n"
					+ "--------------------------------------------------\n \n"
					+ "     0    1    2    3    4    5    6    7    8    9 \n";
		
		for (int row = 0; row < units.length; row++) {
			
			//	Print the ROW numbers
			if(row < 10) {
				str += "\n0" + row + ":  ";
			}
			else {
				str += "\n" + row + ":  ";
			}
			
			//	Print the Units, Std/Hum/Temp noted rented, hum, or temp
			for (int col = 0; col < units[row].length; col++) {
				Unit unit = units[row][col];
				Customer cstmr = units[row][col].getCustomer();
				
				if(row <= 6) {
					if(cstmr != null) {
						str += "S*   "; 
					}
					else {
						str += "S__  ";
					}
					
				}
				
				if(row >= 7 && row <=9) {
					UnitHumidity humUnit = (UnitHumidity) unit;
					if(cstmr != null) {
						str += "H" + humUnit.getHumidityLevel() + "  ";
					}
					else {
						str += "H__  ";
					}
				}
				
				if(row >= 10) {
					UnitTemperature tempUnit = (UnitTemperature) unit;
					if(cstmr != null) {
						str += "T" + tempUnit.getTemperature() + "  ";
					}
					else {
						str += "T__  ";
					}
					
				}
			}
		}
		return str;
	}
	
	/**
	 * Build a string of all information for Storage Location Class
	 */
	public String toString() {
		String str = "Designation: " + designation + "\nUnits: ";
		
		//		Iterate and concat each Unit object		
		for ( int row = 0; row < units.length; row++ ) {
			str = str + "\nUnit Row: " + row;
			for ( int col = 0; col < units[row].length; col++ ) {
				str = str + " Col: " + col;
				str = str + "\n" + units[row][col].toString();
			}
		}
		
		//		Iterate and concat each Customer Object
		str = str + "\nNumber of Customers: " + numOfCustomers;
		for ( int i = 0; i < customers.length; i++ ) {
			Customer cstmr = customers[i];
			
			str = str + "\nCustomer: " + i;
			str = str + "\n" + cstmr.toString();
		}
		return str;
	}

}
