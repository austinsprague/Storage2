import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class UnitHumidityTest{	
	String name = "Test name";
	String phoneNum = "1234567890";
	
	String waSeattleDesignation = "WA02Seattle";
	int width = 5;
	int length = 10;
	int height = 10;
	double locPrice = 20.0;

	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation, locPrice);
	UnitHumidity humUnit = new UnitHumidity(width, length, height, locPrice);

	Customer customer = new Customer(name, phoneNum);
	
	@Test
	public void testUnitHumidityConstructor() {
		assertEquals(locPrice, waSeattle.getBasePrice(), 0.00001);
		assertEquals(length, humUnit.getUnitLength());
		assertEquals(height, humUnit.getUnitHeight());
		assertEquals(height, humUnit.getUnitHeight());
		assertEquals(0.0, humUnit.getHumidityLevel(), 0.0001);
	}
	
	@Test
	public void testCalculatePrice() {
		humUnit.calculatePrice();
		assertEquals(270, humUnit.getPrice(), 0.00001);
	}

	@Test
	public void testCalculatePriceHumidity() {
		int humLevel;
		
		humLevel = 30;
		humUnit.calculatePrice(humLevel);
		assertEquals(30, humUnit.getHumidityLevel(), 0.00001);
		assertEquals(270, humUnit.getPrice(), 0.00001);
		
		humLevel = 29;
		humUnit.calculatePrice(humLevel);
		assertEquals(29, humUnit.getHumidityLevel(), 0.00001);
		assertEquals(290, humUnit.getPrice(), 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCalculatePriceInvalidHumidityLow() {
		int outOfRangeLevel = 19;
		humUnit.calculatePrice(outOfRangeLevel);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCalculatePriceInvalidHumidityHigh() {
		int outOfRangeLevel = 61;
		humUnit.calculatePrice(outOfRangeLevel);
	}
	
	@Test
	public void testRentHumUnitToCustomer() {
		UnitHumidity firstUnit = (UnitHumidity) waSeattle.getUnit(7,0);
		int humidityLevel = 30;
		
		assertEquals(null, firstUnit.getCustomer());
		assertEquals(270, firstUnit.getPrice(), 0.00001);
		
		firstUnit.rent(customer, humidityLevel);
		assertEquals(customer, firstUnit.getCustomer());
		assertEquals(270, firstUnit.getPrice(), 0.00001);
		assertEquals(LocalDate.now(), firstUnit.getStartDate());
		assertEquals(humidityLevel, firstUnit.getHumidityLevel());
	}

}
