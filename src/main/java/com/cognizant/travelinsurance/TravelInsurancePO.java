package com.cognizant.travelinsurance;


import com.cognizant.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class contains methods and properties required to book travelel insurance plan
 * for 2 students, as per project requirements.
 */
public class TravelInsurancePO {
    // Browser automating WebDriver object
    public WebDriver driver = null;

    // WebElement locators for the travel insurance automation.
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

    /**
     * Constructor method.
     * @param driver {@code WebDriver} object to automate the browser.
     */
    public TravelInsurancePO(WebDriver driver){
        this.driver = driver;
        String currPageTitle = BrowserUtils.getTitle(driver);
    }

    /**
     * Method to search for the country for travelling.
     * @param country {@code String} country value to enter in the search box.
     */
    public void searchCountry(String country) {
        BrowserUtils.waitForElement(driver,searchCountryBy);
        BrowserUtils.sendKeys(driver.findElement(searchCountryBy), country);
        BrowserUtils.wait(driver,1);
        BrowserUtils.actionPressEnter(driver);
        BrowserUtils.wait(driver,1);
    }

    /**
     * Method to select age from the age drop-down button for the travellers.
     * @param age {@code String} value for the age you want to select.
     * @param locator {@code org.openqa.selenium.By} locator for the age WebElement.
     */
    public void selectAge(String age, By locator) {
        BrowserUtils.waitForElement(driver, locator);
        BrowserUtils.selectByValue(driver.findElement(locator), age);
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate click on the button {@code Add Another Traveller}.
     */
    public void addAnotherTraveler() {
        BrowserUtils.waitForElement(driver, addAnotherTravelerBy);
        BrowserUtils.clickElement(driver.findElement(addAnotherTravelerBy));
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate click on the {@code Next} button after filling out
     * the country and age details for the traveller.
     */
    public void clickNext() {
        BrowserUtils.clickElement(driver.findElement(nextBy));
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate selection of the travel dates for the travellers.
     */
    public void selectCalendarDates() {
        BrowserUtils.waitForElement(driver, dateSelectBoxBy);
        BrowserUtils.waitForElement(driver, dateFirstBy);
        BrowserUtils.waitForElement(driver, dateSecondBy);
        BrowserUtils.clickElement(driver.findElement(dateFirstBy));
        BrowserUtils.wait(driver, 1);
        BrowserUtils.clickElement(driver.findElement(dateSecondBy));
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate click on the {@code Proceed} button after filling out the
     * traveller's insurance details.
     */
    public void clickProceedButton() {
        BrowserUtils.clickElement(driver.findElement(proceedBy));
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to fill out the required mobile number in the {@code Phone} text-box.
     * @param mobileNumber {@code String} value of the mobile number to fill out in the text-box.
     */
    public void enterMobileNumber(String mobileNumber) {
        BrowserUtils.waitForElement(driver, mobileNumberBy);
        BrowserUtils.sendKeys(driver.findElement(mobileNumberBy), mobileNumber);
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate click on the {@code ViewPlans} button.
     */
    public void clickViewPlans() {
        BrowserUtils.clickElement(driver.findElement(viewPlansBy));
        BrowserUtils.wait(driver, 1);
    }

    /**
     * Method to simulate price filter button selection on the web page
     * with all the insurance plans listed out.
     * @param selectOption {@code String} value of the price filter drop-down button.
     */
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
