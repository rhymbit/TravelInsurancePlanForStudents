package com.cognizant.CarInsurance;

import com.cognizant.Utilities.Navigate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CarInsurancePage2 {
    public WebDriver driver;

    private By place = By.xpath("//*[@id=\"searchInput\"]/div/input");
    private By click = By.xpath("//*[@id=\"react-autowhatever-1--item-0\"]/span");
    private By carbrandclick = By.xpath("//*[@id=\"dvMake\"]/div/ul/div/li[3]/span");
    private By brand = By.xpath("//*[@id=\"modelScroll\"]/li[3]/span");
    private By carfuel = By.xpath("//*[@id=\"Petrol\"]");
    private By variant = By.xpath("//*[@id=\"variantScroll\"]/li[3]/span");
    private By carregisteryear = By.xpath("//*[@id=\"dvRegYear\"]/ul/div/li[1]/span");
    private By name = By.id("name");
    private By email = By.id("email");
    private By phone = By.id("mobileNo");
    private By submit = By.xpath("//*[@id=\"btnLeadDetails\"]");

    public CarInsurancePage2(WebDriver driver){
//        CarInsurancePO page = new CarInsurancePO(driver);
//        driver =  page.clickonProceed();
        this.driver = driver;
    }

    public WebDriver filldetails(){
        driver.findElement(place).sendKeys("MP14");
        Navigate.wait(driver, 5);
        driver.findElement(click).click();
        Navigate.wait(driver, 5);
        driver.findElement(carbrandclick).click();
        Navigate.wait(driver, 5);
        driver.findElement(brand).click();
        Navigate.wait(driver, 5);
        driver.findElement(carfuel).click();
        Navigate.wait(driver, 5);
        driver.findElement(variant).click();
        Navigate.wait(driver, 5);
        driver.findElement(carregisteryear).click();
        Navigate.wait(driver, 5);

        driver.findElement(name).sendKeys("Ayush Saxena");
        Navigate.wait(driver, 5);
        driver.findElement(email).sendKeys("fake@email.com");
        Navigate.wait(driver, 5);
        driver.findElement(phone).sendKeys("987456");
        Navigate.wait(driver, 5);
        driver.findElement(submit).click();

        return driver;
    }
}
