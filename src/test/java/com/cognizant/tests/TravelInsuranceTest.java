package com.cognizant.tests;

import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Navigate;
import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.sql.Driver;
import java.util.Map;

public class TravelInsuranceTest extends TestBase{
    private String browserName;
    private String screenShotPath;
    protected Map<String,String> travelData = null;

    private void setScreenShotPath(String pngFileName) {
        screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
                "travelInsurance", browserName, pngFileName).toString();
    }

    private String countryKey = "country";
    private String age1Key = "age1";
    private String age2Key = "age2";
    private String phoneNumberKey = "phoneNumber";
    private String priceFilterCondition = "Price: Low to High";

    @Test(enabled = true)
    public void testSearchCountry() {
        System.out.println(driver);
        // get homepage instance
        HomePagePO homePage = new HomePagePO(driver);
        // open homepage url
        homePage.openHomePage();
        // click on Travel Insurance Button
        TravelInsurancePO travelInsurancePage = homePage.gotoTravelInsurance();
        // Enter 'France' in country box
        travelInsurancePage.searchCountry(travelData.get(countryKey));
        // Select age '21' for first traveler
        travelInsurancePage.selectAge(travelData.get(age1Key), travelInsurancePage.ageSelector0By);
        // click on add another traveler
        travelInsurancePage.addAnotherTraveler();
        // Select age '22' for second traveler
        travelInsurancePage.selectAge(travelData.get(age2Key), travelInsurancePage.ageSelector1By);
        // click the 'next' button
        travelInsurancePage.clickNext();
        // click on dates from calendar pop-up
        travelInsurancePage.selectCalendarDates();
        // click on the proceed button
        travelInsurancePage.clickProceedButton();
        // enter mobile number
        travelInsurancePage.enterMobileNumber(travelData.get(phoneNumberKey));
        // click on 'View Plans'
        travelInsurancePage.clickViewPlans();
        // select price low-to-high filter
        travelInsurancePage.selectPriceOption(priceFilterCondition);
        // scroll a bit down on the page
        Navigate.scrollWindow(driver, 250);
        // take screenshot of first 3 lowest price
        setScreenShotPath("lowestThreePriceInsurance.png");
        Navigate.screenshot(driver, screenShotPath);
    }

    @Override
    @BeforeClass
    @Parameters("browser")
    protected void testClassSetup(String browser) {
        browserName = browser;
        readExcel = new ReadExcel(0);
        travelData = readExcel.getTravelInsuranceData(0);

//        DriverSetup instance = DriverSetup.getInstance();
//        if (browser.equalsIgnoreCase("edge")){
//            driver = instance.getDriver("edge");
//            browserName = "edge";
//        }
//        else if (browser.equalsIgnoreCase("chrome")) {
//            driver = instance.getDriver("chrome");
//            browserName = "chrome";
//        }
//        else if (browser.equalsIgnoreCase("firefox")) {
//            driver = instance.getDriver("firefox");
//            browserName = "firefox";
//        }
//        else {
//            Reporter.log("Install one of the following browsers to run this project:-");
//            Reporter.log("Microsoft Edge");
//            Reporter.log("Google Chrome");
//            Reporter.log("Firefox");
//            System.exit(1);
//        }
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        readExcel.closeWorkbook();
//        driver.close();
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}
