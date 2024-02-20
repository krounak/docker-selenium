package com.seldocker.Pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterationConfirmation extends AbstractPage {

	@FindBy(id = "go-to-flights-search")
	private WebElement flightConfirmation;
	
	@FindBy(css = "#registration-confirmation-section p b")
	private WebElement confirmationName;
	
	public RegisterationConfirmation(WebDriver driver) {
		super(driver);
	}
	public void goToFlight() {
		this.flightConfirmation.click();
	}
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(flightConfirmation));
		return this.flightConfirmation.isDisplayed();
	}
	
	public String getFirstname() {
		return this.confirmationName.getText();
	}
}
