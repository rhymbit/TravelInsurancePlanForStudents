package com.cognizant.CarInsurance;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.CancellationException;

public class main {
    public static void main(String[] args){
        WebDriver driver;
        CarInsurancePage2 car = new CarInsurancePage2();
        driver = car.filldetails();
        driver.quit();
    }
}
