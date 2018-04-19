import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class UnitTemperatureTest{	
	String name = "Test name";
	String phoneNum = "1234567890";
	
	String waSeattleDesignation = "WA02Seattle";
	int width = 5;
	int length = 10;
	int height = 10;
	double locPrice = 20.0;

	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation, locPrice);
	UnitTemperature TempUnit = new UnitTemperature(width, length, height, locPrice);
	Customer customer = new Customer(name, phoneNum);
	
	@Test
	public void testUnitTemperatureConstructor() {
		assertEquals(locPrice, waSeattle.getBasePrice(), 0.00001);
		assertEquals(length, TempUnit.getUnitLength());
		assertEquals(height, TempUnit.getUnitHeight());
		assertEquals(height, TempUnit.getUnitHeight());
	}
	
	@Test
	public void testCalculatePrice() {
		int sqft = length * width * height;
		double chargePerCuFt = 1.0;
		double charge = (sqft*chargePerCuFt) + locPrice; 
		
		TempUnit.calculatePrice();
		assertEquals(charge, TempUnit.getPrice(), 0.00001);
	}

	@Test
	public void testCalculatePriceTemperature() {
		int tempLevel;
		TempUnit.calculatePrice();
		assertEquals(520, TempUnit.getPrice(), 0.00001);
		
		tempLevel = 49;
		TempUnit.calculatePrice(tempLevel);
		assertEquals(550, TempUnit.getPrice(), 0.00001);
		assertEquals(49, TempUnit.getTemperature(), 0.00001);
		
		tempLevel = 64;
		TempUnit.calculatePrice(tempLevel);
		assertEquals(520, TempUnit.getPrice(), 0.00001);
		assertEquals(64, TempUnit.getTemperature(), 0.00001);
		
		tempLevel = 65;
		TempUnit.calculatePrice(tempLevel);
		assertEquals(550, TempUnit.getPrice(), 0.00001);
		assertEquals(65, TempUnit.getTemperature(), 0.00001);
		
		tempLevel = 50;
		TempUnit.calculatePrice(tempLevel);
		assertEquals(520, TempUnit.getPrice(), 0.00001);
		assertEquals(50, TempUnit.getTemperature(), 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCalculatePriceInvalidTempLow() {
		int outOfRangeLevel = 44;
		TempUnit.calculatePrice(outOfRangeLevel);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCalculatePriceInvalidTempHigh() {
		int outOfRangeLevel = 71;
		TempUnit.calculatePrice(outOfRangeLevel);
	}

	
	@Test
	public void testRentTempUnitToCustomer() {
		UnitTemperature firstUnit = (UnitTemperature) waSeattle.getUnit(11,0);
		assertEquals(null, firstUnit.getCustomer());
		assertEquals(520, firstUnit.getPrice(), 0.00001);
		
		firstUnit.rent(customer,65);
		assertEquals(550, firstUnit.getPrice(), 0.00001);
		
		firstUnit.rent(customer,50);
		assertEquals(520, firstUnit.getPrice(), 0.00001);
		
		firstUnit.rent(customer,49);
		assertEquals(550, firstUnit.getPrice(), 0.00001);
		
		assertEquals(customer, firstUnit.getCustomer());
		assertEquals(LocalDate.now(), firstUnit.getStartDate());
	}

}
