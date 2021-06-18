package com.cognizant.tests;

import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.WebDriver;
import com.cognizant.CarInsurance.CarInsurancePO;
import com.cognizant.CarInsurance.CarInsurancePage2;
import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HealthInsuranceTest {
    public WebDriver driver;
    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
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
            Reporter.log("Microsft Edge");
            Reporter.log("Google Chrome");
            Reporter.log("Firefox");
            System.exit(1);
        }
    }

    @AfterTest
    public void cleanup() {
        Navigate.closeDriver(driver);
    }

    @Test
    public void testCarInsuranceCase(){
        HomePagePO homepage = new HomePagePO(driver);
        homepage.openHomePage();
        Navigate.wait(driver,10);
        homepage.fetchHealthInsuranceMenu();
    }
}
