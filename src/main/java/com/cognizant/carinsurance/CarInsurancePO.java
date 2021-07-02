package com.cognizant.carinsurance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarInsurancePO {
    // Browser automating WebDriver object
    public static WebDriver driver = null;

    // WebElement locator for the travel insurance automation.
    private static By ProceedWithoutCarNumber = By.xpath("//*[contains(text(), 'Proceed')]");

    /**
     * Constructor method.
     * @param driver {@code WebDriver} object to automate the browser.
     */
    public CarInsurancePO(WebDriver driver){
        this.driver = driver;
        driver.findElement(ProceedWithoutCarNumber);
    }

    /**
     * Method to click on Proceed Without Number Button
     */
    public static WebDriver clickonProceed(){
        driver.findElement(ProceedWithoutCarNumber).click();
        return driver;
    }
}
