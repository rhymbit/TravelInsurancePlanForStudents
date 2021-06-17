package com.cognizant.CarInsurance;

import com.cognizant.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarInsurance {
    public WebDriver driver;
    private By ProceedWithoutCarNumber = By.xpath("//*[@id=\"before-tp\"]/div[2]/a");

    public CarInsurance(){
        HomePage home = new HomePage();
        driver = home.gotoTravelInsurance();
    }

    public WebDriver clickonProceed(){
        driver.findElement(ProceedWithoutCarNumber);
        return driver;
    }
}
