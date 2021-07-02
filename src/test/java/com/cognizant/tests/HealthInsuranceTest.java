package com.cognizant.tests;

import com.cognizant.homepage.HomePagePO;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.utilities.Global_VARS;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;

/**
 * This class contains methods and functionalities to simulate
 * Health Insurance requirement for the project.
 */
public class HealthInsuranceTest extends TestBase{
//    private WebDriver driver;

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
     * This method runs at the start of this class.
     * This method creates the necessary configurations required to run the project.
     */
    @BeforeClass
    @Parameters({"browser", "platform", "environment"})
    protected void testClassSetup(@Optional(Global_VARS.BROWSER) String browser,
                                  @Optional(Global_VARS.PLATFORM) String platform,
                                  @Optional(Global_VARS.ENVIRONMENT) String environment) {

        Global_VARS.DEF_BROWSER = System.getProperty("browser", browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform", platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment", environment);


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