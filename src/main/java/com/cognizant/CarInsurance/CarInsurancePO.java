package com.cognizant.CarInsurance;

import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.By;
import com.cognizant.Utilities.Navigate;
import org.openqa.selenium.WebDriver;

public class CarInsurancePO {
    public WebDriver driver = null;
    private By ProceedWithoutCarNumber = By.xpath("//*[@id=\"before-tp\"]/div[2]/a");

    public CarInsurancePO(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver clickonProceed(){

        driver.findElement(ProceedWithoutCarNumber).click();

        return driver;
    }
}
