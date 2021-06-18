package com.cognizant.CarInsurance;

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
    private By phone = By.id("phone");
    private By submit = By.xpath("//*[@id=\"btnLeadDetails\"]");

    public CarInsurancePage2(){
        CarInsurancePO page = new CarInsurancePO();
        driver =  page.clickonProceed();
    }

    public WebDriver filldetails(){
        driver.findElement(place).sendKeys("MP14");
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(click).click();
        driver.findElement(carbrandclick).click();
        driver.findElement(brand).click();
        driver.findElement(carfuel).click();
        driver.findElement(variant).click();
        driver.findElement(carregisteryear).click();

        driver.findElement(name).sendKeys("Ayush Saxena");
        driver.findElement(email).sendKeys("fakeemail.com");
        driver.findElement(phone).sendKeys("987456");

        driver.findElement(submit).click();

        return driver;


    }

}
