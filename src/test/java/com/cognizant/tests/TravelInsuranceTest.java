package com.cognizant.tests;

import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.util.Map;

public class TravelInsuranceTest extends TestBase{
    private WebDriver driver;
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

    @Test(enabled = false, successPercentage = 0)
    public void testSearchCountry() {
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
        BrowserUtils.scrollWindow(driver, 250);
        // take screenshot of first 3 lowest price
        setScreenShotPath("lowestThreePriceInsurance.png");
        BrowserUtils.screenshot(driver, screenShotPath);
    }

    @BeforeClass
    @Parameters({"browser", "platform", "environment"})
    protected void testClassSetup(@Optional(Global_VARS.BROWSER) String browser,
                                  @Optional(Global_VARS.PLATFORM) String platform,
                                  @Optional(Global_VARS.ENVIRONMENT) String environment) {

        Global_VARS.DEF_BROWSER = System.getProperty("browser", browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform", platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment", environment);

        DriverSetup.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                Global_VARS.DEF_PLATFORM,
                Global_VARS.DEF_ENVIRONMENT);

        driver = DriverSetup.getInstance().getDriver();

        browserName = browser;
        readExcel = new ReadExcel(0);
        travelData = readExcel.getTravelInsuranceData(0);
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        readExcel.closeWorkbook();
        DriverSetup.getInstance().closeDriver(driver);
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}
