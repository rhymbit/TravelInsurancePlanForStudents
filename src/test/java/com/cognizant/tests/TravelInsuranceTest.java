package com.cognizant.tests;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class TravelInsuranceTest {
    private WebDriver driver = null;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        DriverSetup instance = DriverSetup.getInstance();
        if (browser.equalsIgnoreCase("edge")){
            driver = instance.getDriver("edge");
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            driver = instance.getDriver("chrome");
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            driver = instance.getDriver("firefox");
        }
        else {
            Reporter.log("Install one of the following browsers to run this project:-");
            Reporter.log("Microsft Edge");
            Reporter.log("Google Chrome");
            Reporter.log("Firefox");
            System.exit(1);
        }
    }

    @AfterTest
    public void cleanup() {
        Navigate.closeDriver(driver);
    }


    @Test
    public void testNavigateToPage() {
        HomePagePO homepage = new HomePagePO(driver);
        homepage.openHomePage();
        TravelInsurancePO travelInsurancePage = homepage.gotoTravelInsurance();
        String pageTitle = Navigate.getTitle(driver);
        Assert.assertEquals(pageTitle, "PolicyBazaar Travel Insurance");
    }
}
