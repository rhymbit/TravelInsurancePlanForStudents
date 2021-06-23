package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.Map;

public abstract class TestBase {
    protected WebDriver driver = null;
    protected ReadExcel readExcel = null;
    protected DriverSetup driverSetup = null;
    protected Map<String,String> carInsuranceData = null;
    protected abstract void testClassSetup(String browser);
    protected abstract void testClassTearDown();
    protected abstract void testMethodsSetup();
    protected abstract void testMethodsTearDown();

    @Parameters({"browser", "platform", "environment"})
    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup(@Optional(Global_VARS.BROWSER) String browser,
                              @Optional(Global_VARS.PLATFORM) String platform,
                              @Optional(Global_VARS.ENVIRONMENT) String environment)
    {
        // Create 'config.properties' file
        Configuration.createConfigurations();

        Global_VARS.DEF_BROWSER = System.getProperty("browser", browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform", platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment", environment);

        DriverSetup.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                                            Global_VARS.DEF_PLATFORM,
                                            Global_VARS.DEF_ENVIRONMENT);
        driver = DriverSetup.getInstance().getDriver();
    }

    @AfterSuite(alwaysRun = true)
    protected void suiteTearDown() {

        DriverSetup.getInstance().closeDriver();
    }
}
