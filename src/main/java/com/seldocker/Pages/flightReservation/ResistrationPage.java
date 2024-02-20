package com.seldocker.Pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResistrationPage extends AbstractPage{
  
	
	
	@FindBy(id = "firstName")
	private WebElement firstName;
	
	@FindBy(id = "lastName")
	private WebElement lastName;
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(name = "street")
	private WebElement street;
	
	@FindBy(name = "city")
	private WebElement city;
	
	@FindBy(name = "zip")
	private WebElement zip;
	
	@FindBy(id = "register-btn")
	private WebElement registerBtn;
	
	public ResistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void enterUserDetails(String firstName,String lastName) {
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
	}
	
	public void enterUserCred(String email,String pass) {
		this.email.sendKeys(email);
		this.password.sendKeys(pass);
	}
	
	public void enterAddress(String street,String city,String zip) {
		this.street.sendKeys(street);
		this.city.sendKeys(city);
		this.zip.sendKeys(zip);
	}

	public void register() {
		this.registerBtn.click();
	}
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(firstName));
		return this.firstName.isDisplayed();
	}
}
