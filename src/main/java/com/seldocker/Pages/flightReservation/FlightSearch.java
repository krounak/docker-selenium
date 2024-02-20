package com.seldocker.Pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearch extends AbstractPage{

	
	@FindBy(id = "passengers")
	private WebElement passgerSelect;
	
	@FindBy(id = "search-flights")
	private WebElement searchFlight;
	
	public FlightSearch(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(passgerSelect));
		return this.passgerSelect.isDisplayed();
	}
	
	public void selectPassenger(String noOfPasserger) {
		Select select = new Select(passgerSelect);
		select.selectByValue(noOfPasserger);
	}

	public void searchFlight() {
		this.searchFlight.click();
	}
	
}
