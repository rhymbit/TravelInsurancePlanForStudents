package com.cognizant.travelinsurance;


import com.cognizant.Utilities.Navigate;
import com.cognizant.homepage.HomePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TravelInsurancePO {
    public WebDriver driver = null;

    public By searchCountryBy = By.xpath("//input[@id='myText']");
    public By countryListingBy = By.xpath("//div[@class='country_listing']");
    public By ageSelector0By = By.xpath("//select[@id='Traveller_0']");
    public By ageSelector1By = By.xpath("//select[@id='Traveller_1']");
    public By addAnotherTravelerBy = By.xpath("//button[@class='secandry_button add_travller']");
    public By nextBy = By.xpath("//button[@class='next next_btn_step']");
    public By dateFirstBy = By.xpath("//td[@class='CalendarDay CalendarDay_1 CalendarDay__default"
            +" CalendarDay__default_2 CalendarDay__today CalendarDay__today_3']");
    public By dateSecondBy = By.xpath("//div[contains(@class,'row pre_main booking-section')]//"+
            "div[contains(@class,'row')]//div[contains(@class,'')]//div[3]//div[1]//table[1]//tbody[1]//tr[4]//td[3]");
    public By proceedBy = By.xpath("//button[@id='GetQuoteButton']");
    public By mobileNumberBy = By.xpath("//input[@id='txtMobileNo']");
    public By viewPlansBy = By.xpath("//button[@id='GetQuoteButton']");

    public TravelInsurancePO(WebDriver driver){
        this.driver = driver;
    }

    public void searchCountry(String country) {
        Navigate.waitForElement(driver,searchCountryBy);
        Navigate.sendKeys(driver.findElement(searchCountryBy), country);
        Navigate.waitForElement(driver, countryListingBy);
        Navigate.clickElement(driver.findElement(countryListingBy));
    }

    public void selectAge(String age, By locator) {
        Navigate.waitForElement(driver, locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(age);
    }

    public void addAnotherTraveler() {
        Navigate.waitForElement(driver, addAnotherTravelerBy);
        Navigate.clickElement(driver.findElement(addAnotherTravelerBy));
    }

    public void clickNext() {
        Navigate.clickElement(driver.findElement(nextBy));
    }

    public void selectCalendarDates() {
        Navigate.waitForElement(driver, dateFirstBy);
        Navigate.clickElement(driver.findElement(dateFirstBy));
        Navigate.clickElement(driver.findElement(dateSecondBy));
    }

    public void clickProceedButton() {
        Navigate.clickElement(driver.findElement(proceedBy));
    }

    public void enterMobileNumber(String moblieNumber) {
        Navigate.waitForElement(driver, mobileNumberBy);
        Navigate.sendKeys(driver.findElement(mobileNumberBy), moblieNumber);
    }

    public void clickViewPlans() {
        Navigate.clickElement(driver.findElement(viewPlansBy));
    }
}
