package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;


/**
 * This is an abstract classes for all other test classes.
 */
public class TestBase {
    // private variables
    protected WebDriver driver;

    protected ReadExcel readExcel = null;

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
    @Parameters({"browser", "platform", "environment"})
    @BeforeClass
    protected void suiteSetup(
            @Optional(Global_VARS.BROWSER) String browser,
            @Optional(Global_VARS.PLATFORM) String platform,
            @Optional(Global_VARS.ENVIRONMENT) String environment)
    {
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
     * This method runs at the end of test suites.
     * It is used for cleanup work after tests executions.
     */
    @AfterSuite(alwaysRun = true)
    protected void suiteTearDown() {
    }
}
