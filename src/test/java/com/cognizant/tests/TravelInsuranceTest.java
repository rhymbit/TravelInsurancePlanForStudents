package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.utilities.Global_VARS;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 * This class contains methods and functionalities to simulate
 * Travel Insurance requirement for the project.
 */
public class TravelInsuranceTest extends TestBase{
    // local variables
//    private WebDriver driver;
    private String browserName;
    private String screenShotPath;
    protected Map<String,String> travelData = null;

    /**
     * Method to set the screenshot path for TravelInsurance test.
     * @param pngFileName Screenshot file name, like, {@code travelData.png}.
     */
    private void setScreenShotPath(String pngFileName) {
        screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
                "travelInsurance", browserName, pngFileName).toString();
    }

    // local variables
    private String countryKey = "country";
    private String age1Key = "age1";
    private String age2Key = "age2";
    private String phoneNumberKey = "phoneNumber";
    private String priceFilterCondition = "Price: Low to High";

    /**
     * Method to simulate travel insurance tests on policybazaar.com
     */
    @Test(enabled = true)
    public void testSearchCountry() {
        try {
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
        } catch (IOException e) {
            Reporter.log("Screenshot file path is not set correctly");
            throw new SkipException("Screenshot file path is not set correctly");
        } catch (TimeoutException e) {
            Reporter.log("WebDriver timeout. Failed to locate a web element.");
            throw new SkipException("WebDriver timeout. Failed to locate a web element.");
        } catch (NoSuchElementException e) {
            Reporter.log("Failed to locate a WebElement object.");
            throw new SkipException("Failed to locate a WebElement object");
        }
    }

    /**
     * This method gets simulation parameters from {@code testng.xml} and passes
     * them to {@link com.cognizant.utilities.DriverSetup} class, which returns a
     * {@code WebDriver} object for automating browser.
     * @param browser Browser name for which driver instance is set for. Currently supported
     *                browsers {@code chrome}, {@code edge}, {@code firefox}.
     * @param platform Operating system, on which automation would run.
     * @param environment   Takes either of two values - {@code local} for running automation locally and
     *                      {@code remote} for running instance using Selenium Grid.
     */
    @BeforeClass
    @Parameters({"browser", "platform", "environment"})
    protected void testClassSetup(@Optional(Global_VARS.BROWSER) String browser,
                                  @Optional(Global_VARS.PLATFORM) String platform,
                                  @Optional(Global_VARS.ENVIRONMENT) String environment) {

        Global_VARS.DEF_BROWSER = System.getProperty("browser", browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform", platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment", environment);

        browserName = browser;
        readExcel = new ReadExcel(0);
        travelData = readExcel.getTravelInsuranceData(0);
    }
    /**
     * Tear down method for closing the WebDriver object and excel workbook file.
     */
    @AfterClass
    protected void testClassTearDown() {
        readExcel.closeWorkbook();
        try {
            driver.close();
            driver.quit();
        } catch (WebDriverException e) {
            Reporter.log("Something went wrong with the web driver object when it was being closed.");
        } catch (Exception e) {
            Reporter.log("Something went wrong when closing the web driver object.");
        }
    }
}
