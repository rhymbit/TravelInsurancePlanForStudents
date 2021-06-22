package com.cognizant.tests;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public abstract class TestBase {
    protected WebDriver driver;
    protected ReadExcel readExcel = null;
    protected Map<String,String> travelData = null;
    protected Map<String,String> carInsuranceData = null;
    protected abstract void testClassSetup(String browser);
    protected abstract void testClassTearDown();
    protected abstract void testMethodsSetup();
    protected abstract void testMethodsTearDown();

    @BeforeSuite
    protected void suiteSetup() {
        Configuration.createConfigurations();
        readExcel = new ReadExcel(0);
        travelData = readExcel.getTravelInsuranceData(0);
    }

    @AfterSuite
    protected void suiteTearDown() {
        readExcel.closeWorkbook();
    }
}
