import static org.junit.Assert.*;
import org.junit.Test;

public class StorageLocationTest {
	String waSeattleCustName1 = "Test Name",
		waSeattleCustPhone1 = "7778889999",
		waSeattleDesignation = "WA02Seattle";
	Double basePrice = 20.0;
	
	Customer waSeattleCust1;
	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation, basePrice);

	@Test
	public void testStorageLocationConstructor() {

		assertEquals(waSeattleDesignation, waSeattle.getDesignation());
		assertEquals(0, waSeattle.getCustomerCount());
		assertEquals("UnitStandard", waSeattle.getUnit(6,  0).getClass().getName());
		assertEquals("UnitHumidity", waSeattle.getUnit(7,  0).getClass().getName());
		assertEquals("UnitTemperature", waSeattle.getUnit(10,  0).getClass().getName());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStorageLocationInvalidDesignation() {
		String invalidDesignation = "Wa02Issaquah";
		
		new StorageLocation(invalidDesignation, basePrice);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStorageLocationInvalidBasePrice() {
		Double outOfRangePrice = -0.01;
	
		new StorageLocation(waSeattleDesignation, outOfRangePrice);
	}
	
	@Test
	public void testAddCustomer() {
		assertEquals(0, waSeattle.getCustomerCount());
		
		waSeattleCust1 = new Customer(waSeattleCustName1, waSeattleCustPhone1);
		waSeattle.addCustomer(waSeattleCust1);
		
		assertEquals(1, waSeattle.getCustomerCount());
	}
	
	@Test
	public void testGetEmptyUnitsByType() {
		int emptyLength;
		Unit unit1;
		
		waSeattleCust1 = new Customer(waSeattleCustName1, waSeattleCustPhone1);
		waSeattle.addCustomer(waSeattleCust1);
		
		emptyLength = waSeattle.getEmptyUnits("UnitStandard").length;
		assertEquals(70, emptyLength);
		
		unit1 = waSeattle.getUnit(0,0);
		unit1.rent(waSeattleCust1);

		emptyLength = waSeattle.getEmptyUnits("UnitStandard").length;
		assertEquals(69, emptyLength);			
	}
	
	@Test
	public void testChargeCustomer() {
		waSeattle.addCustomer(new Customer("Jim Bo", "7778889999"));
		waSeattle.addCustomer(new Customer("Mary Su", "1112223333"));
		Customer jim = waSeattle.getCustomer(0);
		Customer mary = waSeattle.getCustomer(1);

		UnitStandard stdUnit1 = (UnitStandard) waSeattle.getUnit(3,1);
		UnitHumidity humUnit1 = (UnitHumidity) waSeattle.getUnit(7, 4);
		UnitTemperature tempUnit1 = (UnitTemperature) waSeattle.getUnit(10, 0);
		
		stdUnit1.rent(jim);
		humUnit1.rent(jim, 30);
		tempUnit1.rent(mary, 70);
		
		waSeattle.chargeCustomer();
		assertEquals(346.75, jim.getAccountBalance(), 0.0001);
		assertEquals(550.0, mary.getAccountBalance(), 0.0001);
	}
	
}
