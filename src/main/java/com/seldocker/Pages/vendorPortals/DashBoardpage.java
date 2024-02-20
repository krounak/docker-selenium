package com.seldocker.Pages.vendorPortals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seldocker.Pages.flightReservation.AbstractPage;

public class DashBoardpage extends AbstractPage{

	private static final Logger log = LoggerFactory.getLogger(DashBoardpage.class);
	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarning;
	
	@FindBy(id = "annual-earning")
	private WebElement anualEarning;
	
	@FindBy(id = "profit-margin")
	private WebElement profitMargin;
	
	@FindBy(id = "available-inventory")
	private WebElement availableInventory;
	
	@FindBy(css = "#dataTable_filter input")
	private WebElement searchInput;
	
	@FindBy(id = "dataTable_info")
	private WebElement searchResultCount;

	@FindBy(css=".img-profile")
	private WebElement userProfilePictureElement;
	
	@FindBy(linkText = "Logout")
	private WebElement logOutLink;
	
	@FindBy(css = "#logoutModal a")
	private WebElement logOutBtn;
	
	public DashBoardpage(WebDriver driver) {
		super(driver);	
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
		return this.monthlyEarning.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return this.monthlyEarning.getText();
	}
	
	public String getAnuallyEarning() {
		return this.anualEarning.getText();
	}
	
	public String getProfitMargin() {
		return this.profitMargin.getText();
	}
	
	public String getAvailableInventory() {
		return this.availableInventory.getText();
	}
	
	public void searchOrderHistoryBy(String keyword) {
		this.searchInput.sendKeys(keyword);
	}
	
	public int searchResultDown() {
		String resultText = this.searchResultCount.getText();
		String[] arr = resultText.split(" ");
		int count = Integer.parseInt(arr[5]);
		log.info("Result Count {}",count);
		return count;
	}
	
	public void logout() {
		this.userProfilePictureElement.click();
		this.wait.until(ExpectedConditions.visibilityOf(logOutLink));
		this.logOutLink.click();
		this.wait.until(ExpectedConditions.visibilityOf(logOutBtn));
		this.logOutBtn.click();
	}
}
