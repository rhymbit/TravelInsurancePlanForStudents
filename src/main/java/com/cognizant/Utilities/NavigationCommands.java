package com.cognizant.Utilities;

import org.openqa.selenium.WebDriver;

public class NavigationCommands {
    //Previous Page
    public static WebDriver gotoPreviousPage(WebDriver driver)
    {
        driver.navigate().back();
        return driver;
    }

    //Forward Page
    public static WebDriver gotoForwardPage(WebDriver driver)
    {
        driver.navigate().forward();
        return driver;
    }

    //Refresh Page
    public static WebDriver refreshPage(WebDriver driver)
    {
        driver.navigate().refresh();
        return driver;
    }

    //Go to specific URL
    public static WebDriver gotoURL(WebDriver driver, String url)
    {
        driver.navigate().to(url);
        return driver;
    }
    //Closing the Browser
    public static WebDriver closeDriver(WebDriver driver)
    {
        driver.close();
        return driver;
    }

}
