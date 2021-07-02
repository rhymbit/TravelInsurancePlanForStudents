package com.cognizant.tests;

import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.List;

/**
 * This class contains methods and functionalities to simulate
 * Health Insurance requirement for the project.
 */
public class HealthInsuranceTest extends TestBase{
    private WebDriver driver;

    /**
     * Method to simulate health insurance tests on policybazaar.com
     */
    @Test(enabled = true)
    public void HealthInsurance() {
        HomePagePO homepage = new HomePagePO(driver);
        // open homepage url
        homepage.openHomePage();
        BrowserUtils.wait(driver, 10);
        List<WebElement> list = homepage.fetchHealthInsuranceMenu();
        System.out.println("Menu Items are: ");
        for (WebElement element : list) {
            Reporter.log(element.getAttribute("innerHTML"));
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

        try {
            DriverSetup driverSetup = new DriverSetup();
            driverSetup.setDriver(Global_VARS.DEF_BROWSER,
                    Global_VARS.DEF_PLATFORM,
                    Global_VARS.DEF_ENVIRONMENT);
            driver = driverSetup.getDriver();
        } catch (MalformedURLException e) {
            Reporter.log("Selenium grid's Hub URL is not set properly, or is not working");
        }

    }
    /**
     * This method closes the workbook as well as browser windows.
     */
    @AfterClass
    protected void testClassTearDown() {
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