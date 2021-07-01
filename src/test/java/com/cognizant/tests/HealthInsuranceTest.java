package com.cognizant.tests;

import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;

public class HealthInsuranceTest extends TestBase{
    private WebDriver driver;

    @Test(enabled = false)
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
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        DriverSetup.getInstance().closeDriver(driver);
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}