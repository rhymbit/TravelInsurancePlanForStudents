package com.cognizant.homepage;

import com.cognizant.carinsurance.CarInsurancePO;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.configuration.Configuration;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Page Object class that encapsulates the HomePage or the front page
 * of the policybazar.com
 */
public class HomePagePO {
    // web driver instance
    public WebDriver driver = null;

    private String baseUrl = null;

    // WebElement locators for the travel insurance automation.
    private final By travelInsuranceBy = By.xpath("//div[13]//a[1]//div[1]//p[1]");
    private final By carInsuranceBy = By.xpath("//div[@class='container prd-container']//div[4]//a[1]//div[1]//p[1]");
    private final By healthInsuranceMenuBy =  By.cssSelector("body > cclink > div.policywarp > div.ruby-menu-demo-header > div > ul > li.ruby-menu-mega > div > div > div:nth-child(2) > ul");

    /**
     * Constructor to set the current {@code WebDriver} object.
     * @param driver Currently executing {@code WebDriver} object.
     */
    public HomePagePO(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to enter the base url in the address bar and open the homepage.
     */
    public void openHomePage() {
        baseUrl = Configuration.getProperty("baseUrl");
        BrowserUtils.gotoUrl(driver, baseUrl);
        BrowserUtils.wait(driver, 0);
    }

    /**
     * Method to go to the {@code TravelInsurance} page from the home page.
     * @return Returns the Page Object for the TravelInsurance object.
     */
    public TravelInsurancePO gotoTravelInsurance() {
        BrowserUtils.clickElement(driver.findElement(travelInsuranceBy));
        return new TravelInsurancePO(driver);
    }

    /**
     * Method to go to the {@code CarInsurance} page from the home page.
     * @return Returns the Page Object for the CarInsurance object.
     */
    public CarInsurancePO gotoCarInsurance() {
        BrowserUtils.clickElement(driver.findElement(carInsuranceBy));
        return new CarInsurancePO(driver);
    }

    /**
     * Method to fetch HealthInsurance data from the drop-down-list on the
     * homepage.
     * @return {@code List<WebElement>}, a list of WebElement types.
     */
    public List<WebElement> fetchHealthInsuranceMenu() {
        WebElement ListOfMenus =  driver.findElement(healthInsuranceMenuBy);
        List<WebElement> listItem = ListOfMenus.findElements(By.cssSelector("li a span"));
        return listItem;
    }
}
