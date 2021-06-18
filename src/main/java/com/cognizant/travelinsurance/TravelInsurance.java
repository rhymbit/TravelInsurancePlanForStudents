package com.cognizant.travelinsurance;


import com.cognizant.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelInsurance {
    public WebDriver driver;
    public TravelInsurance(WebDriver driver){
        HomePage home = new HomePage();
        home.gotoTravelInsurance();

    }
}
