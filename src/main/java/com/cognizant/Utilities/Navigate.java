package com.cognizant.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Navigate {

    //Previous Page
    public static WebDriver navigateBack(WebDriver driver)
    {
        driver.navigate().back();
        return driver;
    }

    //Forward Page
    public static WebDriver navigateForward(WebDriver driver)
    {
        driver.navigate().forward();
        return driver;
    }

    //Refresh Page
    public static WebDriver refresh(WebDriver driver)
    {
        driver.navigate().refresh();
        return driver;
    }

    //Go to specific URL
    public static WebDriver gotoUrl(WebDriver driver, String url)
    {
        driver.navigate().to(url);
        return driver;
    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void clickElement(WebElement elem) {
        elem.click();
    }

    // implicit wait to load the page contents
    public static void wait(WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    //Closing the Browser
    public static WebDriver closeDriver(WebDriver driver)
    {
        driver.close();
        return driver;
    }

}
