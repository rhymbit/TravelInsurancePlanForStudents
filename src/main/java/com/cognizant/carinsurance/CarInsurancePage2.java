package com.cognizant.carinsurance;

import com.cognizant.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CarInsurancePage2 {
    // Browser automating WebDriver object
    public WebDriver driver;

    // WebElement locators for the travel insurance automation.
    public By placeBy = By.xpath("//*[@id=\"searchInput\"]/div/input");
    public By clickBy = By.xpath("//*[@id=\"react-autowhatever-1--item-0\"]/span");
    public By carBrandClickBy = By.xpath("//*[@id=\"dvMake\"]/div/ul/div/li[3]/span");
    public By brandBy = By.xpath("//*[@id=\"modelScroll\"]/li[3]/span");
    public By carfuelBy = By.xpath("//*[@id=\"Petrol\"]");
    public By variantBy = By.xpath("//*[@id=\"variantScroll\"]/li[3]/span");
    public By carRegisterYearBy = By.xpath("//*[@id=\"dvRegYear\"]/ul/div/li[1]/span");
    public By nameBy = By.id("name");
    public By emailBy = By.id("email");
    public By phoneBy = By.xpath("//*[@id=\"mobileNo\"]");
    public By submitBy = By.xpath("//*[@id=\"btnLeadDetails\"]");

    /**
     * Constructor method.
     * @param driver {@code WebDriver} object to automate the browser.
     */
    public CarInsurancePage2(WebDriver driver){
        CarInsurancePO page = new CarInsurancePO(driver);
        this.driver = driver;
        driver =  page.clickonProceed();
    }

    /**
     * Method to set city in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     * @param cityName {@code String} locator for the age WebElement.
     */
    public void setCity(WebDriver driver, String cityName){
        BrowserUtils.sendKeys(driver.findElement(placeBy), cityName);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        BrowserUtils.clickElement(driver.findElement(clickBy));
    }

    /**
     * Method to set Car Brand in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void setCarBrand(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(carBrandClickBy));

    }

    /**
     * Method to set Car Model in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void setModel(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(brandBy));
    }

    /**
     * Method to set Car Type in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void setType(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(this.carfuelBy));
    }

    /**
     * Method to set Car Variant in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void setVariant(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(this.variantBy));
    }

    /**
     * Method to set Car Registration Year in the input text field.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void setCarRegisterYear(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(carRegisterYearBy));
    }

    /**
     * Method to fill the test cases input in the input text field in the form.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     * @param name {@code String} Name of the customer
     * @param emailInput {@code String} Email of the customer
     * @param phoneInput {@code org.openqa.selenium.WebDriver} object to automate the browser
     */
    public void fillDetailsForm(WebDriver driver, String name,
                                String emailInput, String phoneInput){
        BrowserUtils.wait(driver,5);
        BrowserUtils.sendKeys(driver.findElement(nameBy), name);
        BrowserUtils.sendKeys(driver.findElement(emailBy), emailInput);
        BrowserUtils.sendKeys(driver.findElement(phoneBy), phoneInput);
        BrowserUtils.clickElement(driver.findElement(submitBy));
    }

    /**
     * Method to select age from the age drop-down button for the travellers.
     * @param driver {@code org.openqa.selenium.WebDriver} object to automate the browser
     * @param locator {@code org.openqa.selenium.By} locator for the any form field that
     *                                         has to be cleared before input WebElement.
     */
    public void clearFormData(WebDriver driver, By locator) {
        driver.findElement(locator).clear();
    }

}
