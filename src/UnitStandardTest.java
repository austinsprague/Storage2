import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class UnitStandardTest{	
	String name = "Test name";
	String phoneNum = "1234567890";
	
	String waSeattleDesignation = "WA02Seattle";
	int width = 5;
	int length = 10;
	int height = 10;
	double locPrice = 20.0;

	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation, locPrice);
	UnitStandard stdUnit = new UnitStandard(width, length, height, locPrice);

	Customer customer = new Customer(name, phoneNum);
	
	@Test
	public void testUnitStandardConstructor() {
		assertEquals(locPrice, waSeattle.getBasePrice(), 0.00001);
		assertEquals(length, stdUnit.getUnitLength());
		assertEquals(height, stdUnit.getUnitHeight());
		assertEquals(height, stdUnit.getUnitHeight());
	}
	
	@Test
	public void testCalculatePrice() {
		stdUnit.calculatePrice();
		assertEquals(95, stdUnit.getPrice(), 0.00001);
	}
	
	@Test
	public void testRentStdUnitToCustomer() {
		UnitStandard firstUnit = (UnitStandard) waSeattle.getUnit(0,0);
		
		assertEquals(null, firstUnit.getCustomer());
		assertEquals(95, firstUnit.getPrice(), 0.00001);
		
		firstUnit.rent(customer);
		assertEquals(customer, firstUnit.getCustomer());
		assertEquals(95, firstUnit.getPrice(), 0.00001);
		assertEquals(LocalDate.now(), firstUnit.getStartDate());
	}

}

