package com.cognizant.travelinsurance;


import com.cognizant.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        String currPageTitle = BrowserUtils.getTitle(driver);
    }

    public void searchCountry(String country) {
        BrowserUtils.waitForElement(driver,searchCountryBy);
        BrowserUtils.sendKeys(driver.findElement(searchCountryBy), country);
        BrowserUtils.wait(driver,1);
        BrowserUtils.actionPressEnter(driver);
    }

    public void selectAge(String age, By locator) {
        BrowserUtils.waitForElement(driver, locator);
        BrowserUtils.selectByValue(driver.findElement(locator), age);
        BrowserUtils.wait(driver, 1);
    }

    public void addAnotherTraveler() {
        BrowserUtils.waitForElement(driver, addAnotherTravelerBy);
        BrowserUtils.clickElement(driver.findElement(addAnotherTravelerBy));
        BrowserUtils.wait(driver, 1);
    }

    public void clickNext() {
        BrowserUtils.clickElement(driver.findElement(nextBy));
        BrowserUtils.wait(driver, 1);
    }

    public void selectCalendarDates() {
        BrowserUtils.waitForElement(driver, dateSelectBoxBy);
        BrowserUtils.waitForElement(driver, dateFirstBy);
        BrowserUtils.waitForElement(driver, dateSecondBy);
        BrowserUtils.clickElement(driver.findElement(dateFirstBy));
        BrowserUtils.wait(driver, 1);
        BrowserUtils.clickElement(driver.findElement(dateSecondBy));
        BrowserUtils.wait(driver, 1);
    }

    public void clickProceedButton() {
        BrowserUtils.clickElement(driver.findElement(proceedBy));
        BrowserUtils.wait(driver, 1);
    }

    public void enterMobileNumber(String mobileNumber) {
        BrowserUtils.waitForElement(driver, mobileNumberBy);
        BrowserUtils.sendKeys(driver.findElement(mobileNumberBy), mobileNumber);
        BrowserUtils.wait(driver, 1);
    }

    public void clickViewPlans() {
        BrowserUtils.clickElement(driver.findElement(viewPlansBy));
        BrowserUtils.wait(driver, 1);
    }

    public void selectPriceOption(String selectOption) {
        BrowserUtils.waitForElement(driver, priceFilterBy);
        switch (selectOption.toLowerCase()) {
            case "price: high to low":
                BrowserUtils.selectByIndex(driver.findElement(priceFilterBy), 2);
                break;
            default:
                BrowserUtils.selectByIndex(driver.findElement(priceFilterBy), 1);
                break;
        }

    }
}
