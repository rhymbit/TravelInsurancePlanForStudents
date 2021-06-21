
package com.cognizant.tests;

import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.WebDriver;
import com.cognizant.carinsurance.CarInsurancePO;
import com.cognizant.carinsurance.CarInsurancePage2;
import com.cognizant.Utilities.DriverSetup;
import com.cognizant.Utilities.Navigate;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class CarInsuranceTest {
    private WebDriver driver = null;
    private String screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
            "carInsurance", "carInsurance.png")
            .toString();

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        Configuration.createConfigurations();
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
        // get homepage instance
        HomePagePO homepage = new HomePagePO(driver);
        // open homepage url
        homepage.openHomePage();
        Navigate.wait(driver,10);

        // click on Car Insurance Button
        CarInsurancePO carInsurancePage = homepage.gotoCarInsurance();

        //click on Proceed without Car Number button
        //driver = carInsurancePage.clickonProceed();

        // get fill form instance
        CarInsurancePage2 carinsurancetest = new CarInsurancePage2(driver);

        //sets City in the form
        carinsurancetest.setCity(driver);

        //sets Car Brand in the form
        carinsurancetest.setCarBrand(driver);

        //sets Model Name in the form
        carinsurancetest.setModel(driver);

        //sets Type of Vehicle in the form
        carinsurancetest.setType(driver);

        //sets Variant of Vehicle in the form
        carinsurancetest.setVariant(driver);

        //sets Car Registration Year in the form
        carinsurancetest.setCarRegisterYear(driver);

        //sets Name, Email and Phone in the form
        carinsurancetest.fillDetailsForm(driver);

    }
}
