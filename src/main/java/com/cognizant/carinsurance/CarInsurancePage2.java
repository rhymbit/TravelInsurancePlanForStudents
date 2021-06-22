package com.cognizant.carinsurance;

import com.cognizant.Utilities.Navigate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CarInsurancePage2 {
    public WebDriver driver;

    private By placeBy = By.xpath("//*[@id=\"searchInput\"]/div/input");
    private By clickBy = By.xpath("//*[@id=\"react-autowhatever-1--item-0\"]/span");
    private By carBrandClickBy = By.xpath("//*[@id=\"dvMake\"]/div/ul/div/li[3]/span");
    private By brandBy = By.xpath("//*[@id=\"modelScroll\"]/li[3]/span");
    private By carfuelBy = By.xpath("//*[@id=\"Petrol\"]");
    private By variantBy = By.xpath("//*[@id=\"variantScroll\"]/li[3]/span");
    private By carRegisterYearBy = By.xpath("//*[@id=\"dvRegYear\"]/ul/div/li[1]/span");
    private By nameBy = By.id("name");
    private By emailBy = By.id("email");
    private By phoneBy = By.xpath("//*[@id=\"mobileNo\"]");
    private By submitBy = By.xpath("//*[@id=\"btnLeadDetails\"]");

    public CarInsurancePage2(WebDriver driver){
        CarInsurancePO page = new CarInsurancePO(driver);
        this.driver = driver;
        driver =  page.clickonProceed();
    }

    public void setCity(WebDriver driver, String cityName){
        Navigate.sendKeys(driver.findElement(placeBy), cityName);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        Navigate.clickElement(driver.findElement(clickBy));
    }

    public void setCarBrand(WebDriver driver){
        Navigate.wait(driver,5);
        Navigate.clickElement(driver.findElement(carBrandClickBy));

    }

    public void setModel(WebDriver driver){
        Navigate.wait(driver,5);
        Navigate.clickElement(driver.findElement(brandBy));
    }

    public void setType(WebDriver driver){
        Navigate.wait(driver,5);
        Navigate.clickElement(driver.findElement(this.carfuelBy));
    }

    public void setVariant(WebDriver driver){
        Navigate.wait(driver,5);
        Navigate.clickElement(driver.findElement(this.variantBy));
    }

    public void setCarRegisterYear(WebDriver driver){
        Navigate.wait(driver,5);
        Navigate.clickElement(driver.findElement(carRegisterYearBy));
    }

    public void fillDetailsForm(WebDriver driver, String name,
                                String fakeEmail, String invalidPhone){
        Navigate.wait(driver,5);
        Navigate.sendKeys(driver.findElement(nameBy), name);
        Navigate.sendKeys(driver.findElement(emailBy), fakeEmail);
        Navigate.sendKeys(driver.findElement(phoneBy), invalidPhone);
        Navigate.clickElement(driver.findElement(submitBy));
    }

}
