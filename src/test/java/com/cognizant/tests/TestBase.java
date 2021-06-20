package com.cognizant.tests;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public abstract class TestBase {
    protected WebDriver driver;
    protected abstract void testClassSetup(String browser);
    protected abstract void testClassTearDown();
    protected abstract void testMethodsSetup();
    protected abstract void testMethodsTearDown();

    @BeforeSuite
    protected void suiteSetup() {
        Configuration.createConfigurations();
    }

    @AfterSuite
    protected void suiteTearDown() {
    }
}
