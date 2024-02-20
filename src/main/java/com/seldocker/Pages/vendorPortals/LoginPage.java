package com.seldocker.Pages.vendorPortals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.seldocker.Pages.flightReservation.AbstractPage;

public class LoginPage extends AbstractPage{

	
	@FindBy(id = "username")
	private WebElement userName;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(id = "login")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.loginBtn));
		return this.loginBtn.isDisplayed();
	}

	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void login(String userName,String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.loginBtn.click();
	}
}
