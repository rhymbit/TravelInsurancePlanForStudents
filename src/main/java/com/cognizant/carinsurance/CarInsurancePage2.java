package com.cognizant.carinsurance;

import com.cognizant.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CarInsurancePage2 {
    public WebDriver driver;

    public By placeBy = By.xpath("//*[@id=\"searchInput\"]/div/input");
    public By clickBy = By.xpath("//*[@id=\"react-autowhatever-1--item-0\"]/span");
    public By carBrandClickBy = By.xpath("//*[@id=\"dvMake\"]/div/ul/div/li[3]/span");
    public By brandBy = By.xpath("//*[@id=\"modelScroll\"]/li[3]/span");
    public By carfuelBy = By.xpath("//*[@id=\"Petrol\"]");
    public By variantBy = By.xpath("//*[@id=\"variantScroll\"]/li[3]/span");
    public By carRegisterYearBy = By.xpath("//*[@id=\"dvRegYear\"]/ul/div/li[1]/span");
    public By nameBy = By.id("name");
    public By emailBy = By.id("email");
    public By phoneBy = By.xpath("//*[@id=\"mobileNo\"]");
    public By submitBy = By.xpath("//*[@id=\"btnLeadDetails\"]");

    public CarInsurancePage2(WebDriver driver){
        CarInsurancePO page = new CarInsurancePO(driver);
        this.driver = driver;
        driver =  page.clickonProceed();
    }

    public void setCity(WebDriver driver, String cityName){
        BrowserUtils.sendKeys(driver.findElement(placeBy), cityName);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        BrowserUtils.clickElement(driver.findElement(clickBy));
    }

    public void setCarBrand(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(carBrandClickBy));

    }

    public void setModel(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(brandBy));
    }

    public void setType(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(this.carfuelBy));
    }

    public void setVariant(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(this.variantBy));
    }

    public void setCarRegisterYear(WebDriver driver){
        BrowserUtils.wait(driver,5);
        BrowserUtils.clickElement(driver.findElement(carRegisterYearBy));
    }

    public void fillDetailsForm(WebDriver driver, String name,
                                String fakeEmail, String invalidPhone){
        BrowserUtils.wait(driver,5);
        BrowserUtils.sendKeys(driver.findElement(nameBy), name);
        BrowserUtils.sendKeys(driver.findElement(emailBy), fakeEmail);
        BrowserUtils.sendKeys(driver.findElement(phoneBy), invalidPhone);
        BrowserUtils.clickElement(driver.findElement(submitBy));
    }

    public void clearFormData(WebDriver driver, By locator) {
        driver.findElement(locator).clear();
    }

}
