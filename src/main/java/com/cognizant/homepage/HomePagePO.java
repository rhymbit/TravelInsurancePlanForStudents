package com.cognizant.homepage;

import com.cognizant.CarInsurance.CarInsurancePO;
import com.cognizant.healthinsurance.HealthInsurancePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePO {
    // web driver instance
    public WebDriver driver = null;

    private By travelInsuranceBy = By.xpath("//div[13]//a[1]//div[1]//p[1]");
    private By carInsuranceBy = By.xpath("//div[@class='container prd-container']//div[4]//a[1]//div[1]//p[1]");
    private By healthInsuranceBy = By.xpath("//div[@class='container prd-container']//div[2]//a[1]//div[1]//p[1]");

    public HomePagePO(WebDriver driver) {
        this.driver = driver;
    }

    public TravelInsurancePO gotoTravelInsurance() {
        driver.findElement(travelInsuranceBy).click();
        return new TravelInsurancePO(driver);
    }

    public CarInsurancePO gotoCarInsurance() {
        driver.findElement(carInsuranceBy).click();
        return new CarInsurancePO(driver);
    }

    public HealthInsurancePO gotoHealthInsurance() {
        driver.findElement(healthInsuranceBy).click();
        return new HealthInsurancePO(driver);
    }
//
//    public void fetchHealthInsuranceMenu() {
//        List<WebElement> ListofMenus = driver.findElements(HealthInsuranc);
//        System.out.println("Menu Items are: ");
//        for (WebElement i : ListofMenus) {
//            System.out.println(i.getText());
//        }
//    }
}
