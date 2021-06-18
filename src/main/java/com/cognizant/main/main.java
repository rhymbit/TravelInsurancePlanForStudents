package com.cognizant.main;

import com.cognizant.CarInsurance.CarInsurancePage2;
import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.CancellationException;

public class main {
    public static void main(String[] args){
        // would create `config.properties' file in the root dir of the folder
        Configuration.createConfigurations();

        WebDriver driver;
        CarInsurancePage2 car = new CarInsurancePage2();
        driver = car.filldetails();
        driver.quit();
    }
}
