package com.seldocker.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.seldocker.listener.TestListener;
import com.seldocker.util.Config;
import com.seldocker.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public abstract class BaseTest {

	protected WebDriver driver;
	
	@BeforeSuite
	public void setUpConfig() {
		Config.initalize();
	}
	
	@BeforeTest
	public void setDriver(ITestContext context) throws MalformedURLException {
		if(Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))){
			this.driver= getRemoteDriver();
		}
		else {
			this.driver = getLocalDriver();
			driver.manage().window().maximize();
		}
		context.setAttribute(Constants.DRIVER, this.driver);
		
//		if(Boolean.getBoolean("selenium.grid.enabled")) {
//			this.driver= getRemoteDriver();
//		}
//		
	}
	
	private WebDriver getRemoteDriver() throws MalformedURLException {
		// https://localhost:4444/wd/hub
		Capabilities capabilities = new ChromeOptions();
		if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			capabilities= new FirefoxOptions();
		}
//		if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
//			capabilities = new ChromeOptions();
//		}
//		else {
//			capabilities = new FirefoxOptions();
//		}
		String urlFormate = Config.get(Constants.GRID_URL_FORMATE);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormate, hubHost);
		return new RemoteWebDriver(new URL(url),capabilities);
	}
	
	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}
