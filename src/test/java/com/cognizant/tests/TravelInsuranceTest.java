package com.cognizant.tests;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class TravelInsuranceTest extends TestBase{
    private String screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
            "travelInsurance", "lowestThreeInsurance.png")
            .toString();

    @Test(enabled = false)
    public void testSearchCountry() {
        // get homepage instance
        HomePagePO homePage = new HomePagePO(driver);
        // open homepage url
        homePage.openHomePage();
        // click on Travel Insurance Button
        TravelInsurancePO travelInsurancePage = homePage.gotoTravelInsurance();
        Navigate.wait(driver, 0);
        // Enter 'France' in country box
        travelInsurancePage.searchCountry(travelData.get("country")); // read from apache poi excel
        // Select age '21' for first traveler
        travelInsurancePage.selectAge(travelData.get("age1"), travelInsurancePage.ageSelector0By); // read from apache poi excel
        // click on add another traveler
        travelInsurancePage.addAnotherTraveler();
        // Select age '22' for second traveler
        travelInsurancePage.selectAge(travelData.get("age2"), travelInsurancePage.ageSelector1By); // read from apache poi excel
        // click the 'next' button
        travelInsurancePage.clickNext();
        // click on dates from calendar pop-up
        travelInsurancePage.selectCalendarDates();
        // click on the proceed button
        travelInsurancePage.clickProceedButton();
        // enter mobile number
        travelInsurancePage.enterMobileNumber(travelData.get("phoneNumber")); // read from apache poi excel
        // click on 'View Plans'
        travelInsurancePage.clickViewPlans();
        // select price low-to-high filter
        travelInsurancePage.selectPriceOption("Price: Low to High");
        // scroll a bit down on the page
        Navigate.scrollWindow(driver, 250);
        // take screenshot of first 3 lowest price
        Navigate.screenshot(driver, screenShotPath);
    }

    @Override
    @BeforeClass
    @Parameters("browser")
    protected void testClassSetup(String browser) {
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
            Reporter.log("Microsoft Edge");
            Reporter.log("Google Chrome");
            Reporter.log("Firefox");
            System.exit(1);
        }
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        Navigate.closeDriver(driver);
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}
