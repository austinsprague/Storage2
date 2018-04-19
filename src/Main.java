
public class Main {

	public static void main(String[] args) {
		String waSeattleDesignation = "WA02Seattle";
		double waSeattleBasePrice = 20.0;
		
		//	Initialize Storage Location with designation
		StorageLocation waSeattle = new StorageLocation(waSeattleDesignation, waSeattleBasePrice);
		
		//	Add the customer to the Customer Array at the Storage Location
		waSeattle.addCustomer(new Customer("Jim Bo", "7778889999"));
		waSeattle.addCustomer(new Customer("Mary Su", "1112223333"));
		System.out.println("CUSTOMER COUNT--> " + waSeattle.getCustomerCount());
		
		// CHANGE:Get Empty Unit types
		String unitType1 = "UnitHumidity";
		System.out.println("EMPTY " + unitType1 + " UNITS COUNT--> " + waSeattle.getEmptyUnits(unitType1).length);
		
		//	Rent Unit to Customer
		Customer jim = waSeattle.getCustomer(0);
		Customer mary = waSeattle.getCustomer(1);
		
		UnitStandard stdUnit1 = (UnitStandard) waSeattle.getUnit(3,1);
		UnitHumidity humUnit1 = (UnitHumidity) waSeattle.getUnit(7, 4);
		UnitTemperature tempUnit1 = (UnitTemperature) waSeattle.getUnit(10, 0);
		
		stdUnit1.rent(jim);
		humUnit1.rent(jim, 30);
		tempUnit1.rent(mary, 70);
		
		//	Charge Customers and Print the rent balance for Jim & Mary
		waSeattle.chargeCustomer();
		System.out.println("Jim's account balance--> " + jim.getAccountBalance());
		System.out.println("Mary's account balance--> " + mary.getAccountBalance());
		
		// GET UNITS BY CUSTOMER
		System.out.println("Jim's unit count--> " + waSeattle.getUnitsForCustomer(jim).length);
		System.out.println("Mary's unit count--> " + waSeattle.getUnitsForCustomer(mary).length);
		
		//	PRINT EMPTY UNIT COUNT BY TYPE
		System.out.println("EMPTY " + unitType1 + " UNITS COUNT--> " + waSeattle.getEmptyUnits(unitType1).length);
		
		// 	PRINT UNIT MAP
		System.out.println(waSeattle.unitMap());
	}

}

