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
    protected DriverSetup instance;
    protected WebDriver driver;
    protected ReadExcel readExcel = null;
    protected DriverSetup driverSetup = null;
    protected Map<String,String> carInsuranceData = null;
    protected abstract void testClassTearDown();
    protected abstract void testMethodsSetup();
    protected abstract void testMethodsTearDown();

    @Parameters({"browser", "platform", "environment"})
    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup()
    {
        // Create 'config.properties' file
        Configuration.createConfigurations();
    }

    @AfterSuite(alwaysRun = true)
    protected void suiteTearDown() {
    }
}
