package com.cognizant.tests;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;

public class HealthInsuranceTest extends TestBase{

    @Test
    public void HealthInsurance() {
        HomePagePO homepage = new HomePagePO(driver);
        // open homepage url
        homepage.openHomePage();
        Navigate.wait(driver, 10);
        List<WebElement> list = homepage.fetchHealthInsuranceMenu();
        System.out.println("Menu Items are: ");
        for (WebElement element : list) {
            Reporter.log(element.getAttribute("innerHTML"));
        }
    }

    @Override
    @BeforeClass
    @Parameters("browser")
    protected void testClassSetup(String browser) {
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
            Reporter.log("Microsoft Edge");
            Reporter.log("Google Chrome");
            Reporter.log("Firefox");
            System.exit(1);
        }
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        Navigate.closeDriver(driver);
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}