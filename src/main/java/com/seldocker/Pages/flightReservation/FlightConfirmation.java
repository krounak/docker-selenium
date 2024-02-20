package com.seldocker.Pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmation extends AbstractPage{

	private static final Logger log = LoggerFactory.getLogger(FlightConfirmation.class);
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
	private WebElement flightConfrimationElement;
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
	private WebElement totalPriceElement;
	
	public FlightConfirmation(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(flightConfrimationElement));
		return this.flightConfrimationElement.isDisplayed();
	}

	public String getPrice() {
		String confiramtion = this.flightConfrimationElement.getText();
		String price = this.totalPriceElement.getText();
		log.info("Flight Confimration: {}",confiramtion);
		log.info("Flight Price: {}",price);
		return this.totalPriceElement.getText();
	}
	
}

