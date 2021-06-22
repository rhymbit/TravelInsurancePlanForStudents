package com.cognizant.travelinsurance;


import com.cognizant.Utilities.Navigate;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TravelInsurancePO {
    public WebDriver driver = null;

    public By searchCountryBy = By.xpath("//input[@id='myText']");
    public By countryScrollBy = By.xpath("//div[@class='country_scroll']");
    public By countryListingBy = By.xpath("//div[@class='country_listing']");
    public By ageSelector0By = By.xpath("//select[@id='Traveller_0']");
    public By ageSelector1By = By.xpath("//select[@id='Traveller_1']");
    public By addAnotherTravelerBy = By.xpath("//button[@class='secandry_button add_travller']");
    public By nextBy = By.xpath("//button[@class='next next_btn_step']");
    public By dateSelectBoxBy = By.xpath("//div[@class='DayPicker_transitionContainer DayPicker_transitionContainer_1"+
            " DayPicker_transitionContainer__horizontal DayPicker_transitionContainer__horizontal_2']");
    public By dateFirstBy = By.xpath("//td[@role='button' and @tabindex='0']");
    public By dateSecondBy = By.xpath("//td[@role='button' and @tabindex='0']"+
            "/following::td[contains(@aria-label,'available')][1]");
    public By proceedBy = By.xpath("//button[@id='GetQuoteButton']");
    public By mobileNumberBy = By.xpath("//input[@id='txtMobileNo']");
    public By viewPlansBy = By.xpath("//button[@id='GetQuoteButton']");
    public By priceFilterBy = By.xpath("//select[@class='sort_select']");

    public TravelInsurancePO(WebDriver driver){
        this.driver = driver;
        String currPageTitle = Navigate.getTitle(driver);
    }

    public void searchCountry(String country) {
        Navigate.waitForElement(driver,searchCountryBy);
        Navigate.sendKeys(driver.findElement(searchCountryBy), country);
        Navigate.wait(driver,5);
        Navigate.actionPressEnter(driver);
    }

    public void selectAge(String age, By locator) {
        Navigate.waitForElement(driver, locator);
        Navigate.selectByValue(driver.findElement(locator), age);
    }

    public void addAnotherTraveler() {
        Navigate.waitForElement(driver, addAnotherTravelerBy);
        Navigate.clickElement(driver.findElement(addAnotherTravelerBy));
    }

    public void clickNext() {
        Navigate.clickElement(driver.findElement(nextBy));
    }

    public void selectCalendarDates() {
        Navigate.waitForElement(driver, dateSelectBoxBy);
        Navigate.waitForElement(driver, dateFirstBy);
        Navigate.waitForElement(driver, dateSecondBy);
        Navigate.clickElement(driver.findElement(dateFirstBy));
        Navigate.clickElement(driver.findElement(dateSecondBy));
    }

    public void clickProceedButton() {
        Navigate.clickElement(driver.findElement(proceedBy));
    }

    public void enterMobileNumber(String mobileNumber) {
        Navigate.waitForElement(driver, mobileNumberBy);
        Navigate.sendKeys(driver.findElement(mobileNumberBy), mobileNumber);
    }

    public void clickViewPlans() {
        Navigate.clickElement(driver.findElement(viewPlansBy));
    }

    public void selectPriceOption(String selectOption) {
        Navigate.waitForElement(driver, priceFilterBy);
        switch (selectOption.toLowerCase()) {
            case "price: high to low":
                Navigate.selectByIndex(driver.findElement(priceFilterBy), 2);
                break;
            default:
                Navigate.selectByIndex(driver.findElement(priceFilterBy), 1);
                break;
        }

    }
}
