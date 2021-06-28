package com.cognizant.carinsurance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarInsurancePO {
    public static WebDriver driver = null;
    private static By ProceedWithoutCarNumber = By.xpath("//*[contains(text(), 'Proceed')]");

    public CarInsurancePO(WebDriver driver){
        this.driver = driver;
        driver.findElement(ProceedWithoutCarNumber);
    }

    public static WebDriver clickonProceed(){
        driver.findElement(ProceedWithoutCarNumber).click();
        return driver;
    }
}
