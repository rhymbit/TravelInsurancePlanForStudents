package com.cognizant.homepage;

import com.cognizant.CarInsurance.CarInsurancePO;
import com.cognizant.Utilities.Navigate;
import com.cognizant.configuration.Configuration;
import com.cognizant.healthinsurance.HealthInsurancePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ObjectInputFilter;
import java.util.concurrent.TimeUnit;

public class HomePagePO {
    // web driver instance
    public WebDriver driver = null;

    private String baseUrl = null;
    private final By travelInsuranceBy = By.xpath("//div[13]//a[1]//div[1]//p[1]");
    private final By carInsuranceBy = By.xpath("//div[@class='container prd-container']//div[4]//a[1]//div[1]//p[1]");
    private final By healthInsuranceBy = By.xpath("//div[@class='container prd-container']//div[2]//a[1]//div[1]//p[1]");

    public HomePagePO(WebDriver driver) {
        this.driver = driver;
    }

    // method to locate baseUrl in the browser.
    // this method should be called very first at the start
    public void openHomePage() {
        baseUrl = Configuration.getProperty("baseUrl");
        Navigate.gotoUrl(driver, baseUrl);
        Navigate.wait(driver, 0);
    }

    public TravelInsurancePO gotoTravelInsurance() {
        Navigate.clickElement(driver.findElement(travelInsuranceBy));
        return new TravelInsurancePO(driver);
    }

    public CarInsurancePO gotoCarInsurance() {
        Navigate.clickElement(driver.findElement(carInsuranceBy));
        return new CarInsurancePO(driver);
    }

    public HealthInsurancePO gotoHealthInsurance() {
        Navigate.clickElement(driver.findElement(healthInsuranceBy));
        return new HealthInsurancePO(driver);
    }
}
