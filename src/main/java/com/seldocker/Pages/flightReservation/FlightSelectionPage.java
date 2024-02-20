package com.seldocker.Pages.flightReservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightSelectionPage extends AbstractPage{

	@FindBy(name = "departure-flight")
	private List<WebElement> departureFlights;
	
	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlights;
	
	@FindBy(id = "confirm-flights")
	private WebElement confirmFlights;
	
	
	public FlightSelectionPage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(confirmFlights));
		return this.confirmFlights.isDisplayed();
	}
	
	public void selectFlight() {
		int random = ThreadLocalRandom.current().nextInt(0, departureFlights.size());
		departureFlights.get(random).click();
		this.arrivalFlights.get(random).click();
	}
	
	public void confirmFlight() {
		this.confirmFlights.click();
	}

}
