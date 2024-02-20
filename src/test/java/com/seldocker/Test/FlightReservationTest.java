package com.seldocker.Test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.seldocker.Pages.flightReservation.FlightConfirmation;
import com.seldocker.Pages.flightReservation.FlightSearch;
import com.seldocker.Pages.flightReservation.FlightSelectionPage;
import com.seldocker.Pages.flightReservation.RegisterationConfirmation;
import com.seldocker.Pages.flightReservation.ResistrationPage;
import com.seldocker.model.FlightReservationTestData;
import com.seldocker.util.Config;
import com.seldocker.util.Constants;
import com.seldocker.util.JsonUtil;

public class FlightReservationTest extends BaseTest {

	
	private FlightReservationTestData testdata;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setParameter(String testDataPath) {
		this.testdata= JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
		
	}
	
	@Test
	public void userRegisterationTest() {
		ResistrationPage resistrationPage = new ResistrationPage(driver);
		resistrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(resistrationPage.isAt());
		resistrationPage.enterUserDetails(testdata.firstName(), testdata.lastName());
		resistrationPage.enterUserCred(testdata.email(),testdata.password());
		resistrationPage.enterAddress(testdata.street(), testdata.city(), testdata.zip());
		resistrationPage.register();
	}
	
	@Test(dependsOnMethods = "userRegisterationTest")
	public void registertationConfirmationPage() {
		RegisterationConfirmation registerConfirmation = new RegisterationConfirmation(driver);
		Assert.assertTrue(registerConfirmation.isAt());
		Assert.assertEquals(registerConfirmation.getFirstname(), testdata.firstName());
		registerConfirmation.goToFlight();
	}
	
	@Test(dependsOnMethods = "registertationConfirmationPage")
	public void flightsearch() {
		FlightSearch flightSearch = new FlightSearch(driver);
		Assert.assertTrue(flightSearch.isAt());
		flightSearch.selectPassenger(testdata.passerngerCount());
		flightSearch.searchFlight();
	}
	
	@Test(dependsOnMethods = "flightsearch")
	public void flightSelection() {
		FlightSelectionPage flightSelection = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelection.isAt());
		flightSelection.selectFlight();
		flightSelection.confirmFlight();
	}
	
	@Test(dependsOnMethods = "flightSelection")
	public void flightReservationConfirmation() {
		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);
		Assert.assertTrue(flightConfirmation.isAt());
		Assert.assertEquals(flightConfirmation.getPrice(), testdata.expectedPrice());
	}
	
	
}
