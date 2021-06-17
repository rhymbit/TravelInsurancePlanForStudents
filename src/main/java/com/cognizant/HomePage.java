package com.cognizant;

import com.cognizant.Utilities.DriverSetup;
import com.cognizant.travelinsurance.TravelInsurance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public WebDriver driver = DriverSetup.setupChromeDriver();
    private By travelInsuranceAnchor = By.xpath("//*/section/div[13]/a");
    private By CarInsuranceAnchor = By.xpath("//*/section/div[4]/a");
    private By HealthInsuranceMenu = By.xpath("/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/ul/li");

    public WebDriver gotoTravelInsurance() {
        driver.findElement(travelInsuranceAnchor).click();
        return driver;
    }

    public WebDriver gotoCarInsurance(){
        driver.findElement(CarInsuranceAnchor).click();
        return driver;
    }

    public void fetchHealthInsuranceMenu(){
        List<WebElement> ListofMenus = driver.findElements(HealthInsuranceMenu);
        System.out.println("Menu Items are: ");
        for( WebElement i: ListofMenus){
            System.out.println(i.getText());
    }
}
