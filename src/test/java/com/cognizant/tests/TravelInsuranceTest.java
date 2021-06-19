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

public class TravelInsuranceTest {
    private WebDriver driver = null;
    private String screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
            "travelInsurance", "lowestThreeInsurance.png")
            .toString();

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        Configuration.createConfigurations();
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


//    @Test(alwaysRun = false)
//    public void testNavigateToPage() {
//        HomePagePO homepage = new HomePagePO(driver);
//        homepage.openHomePage();
//        TravelInsurancePO travelInsurancePage = homepage.gotoTravelInsurance();
//        String pageTitle = Navigate.getTitle(driver);
//        Assert.assertEquals(pageTitle, "PolicyBazaar Travel Insurance");
//    }

    @Test
    public void testSearchCountry() {
        // get homepage instance
        HomePagePO homePage = new HomePagePO(driver);
        // open homepage url
        homePage.openHomePage();
        // click on Travel Insurance Button
        TravelInsurancePO travelInsurancePage = homePage.gotoTravelInsurance();
        Navigate.wait(driver, 0);
        // Enter 'France' in country box
        travelInsurancePage.searchCountry("France"); // read from apache poi excel
        // Select age '21' for first traveler
        travelInsurancePage.selectAge("21", travelInsurancePage.ageSelector0By); // read from apache poi excel
        // click on add another traveler
        travelInsurancePage.addAnotherTraveler();
        // Select age '22' for second traveler
        travelInsurancePage.selectAge("22", travelInsurancePage.ageSelector1By); // read from apache poi excel
        // click the 'next' button
        travelInsurancePage.clickNext();
        // click on dates from calendar pop-up
        travelInsurancePage.selectCalendarDates();
        // click on the proceed button
        travelInsurancePage.clickProceedButton();
        // enter mobile number
        travelInsurancePage.enterMobileNumber("9876543210"); // read from apache poi excel
        // click on 'View Plans'
        travelInsurancePage.clickViewPlans();
        // select price low-to-high filter
        travelInsurancePage.selectPriceOption("Price: Low to High");
        // scroll a bit down on the page
        Navigate.scrollWindow(driver, 250);
        // take screenshot of first 3 lowest price
        Navigate.screenshot(driver, screenShotPath);
    }
}
