package com.seldocker.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.seldocker.Pages.vendorPortals.DashBoardpage;
import com.seldocker.Pages.vendorPortals.LoginPage;
import com.seldocker.model.VendorPortalTestData;
import com.seldocker.util.Config;
import com.seldocker.util.Constants;
import com.seldocker.util.JsonUtil;


public class VendorPortalTest extends BaseTest {

	private LoginPage login;
	private DashBoardpage dashboard;
	private VendorPortalTestData testdata;
	
	@BeforeTest
	@Parameters("testDataPath")
     public void setPageObject(String testDataPath) {
		
		this.login = new LoginPage(driver);
		this.dashboard = new DashBoardpage(driver);
		this.testdata = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
	}
	
	@Test
	public void loginPage() {
		
		login.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(login.isAt());
		login.login(testdata.username(), testdata.password());
	}
	
	@Test(dependsOnMethods = "loginPage")
	public void dashBoardTest() {
		
		Assert.assertTrue(dashboard.isAt());
		
		Assert.assertEquals(dashboard.getMonthlyEarning(), testdata.monthlyEarning());
		Assert.assertEquals(dashboard.getAnuallyEarning(), testdata.annualEarning());
		Assert.assertEquals(dashboard.getAvailableInventory(), testdata.availableInventory());
		Assert.assertEquals(dashboard.getProfitMargin(), testdata.profitMargin());
		
		dashboard.searchOrderHistoryBy(testdata.searchkeyword());
		Assert.assertEquals(dashboard.searchResultDown(), testdata.searchResultsCount());
		
		dashboard.logout();
		Assert.assertTrue(login.isAt());
	}
	
	
}
