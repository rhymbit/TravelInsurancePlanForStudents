package com.cognizant.Utilities;

import java.util.concurrent.TimeUnit;
import com.cognizant.Utilities.NavigationCommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
    //This setups Firefox Driver.
    public static WebDriver setupFirefoxDriver()
    {
        // declaration and instantiation of objects/variables
        String baseUrl = "https://www.policybazaar.com/";
        System.setProperty("webdriver.gecko.driver","../../../../WebDriver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        NavigationCommands.gotoURL(driver, baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    //This setups Firefox Driver.
    public static WebDriver setupChromeDriver()
    {
        // declaration and instantiation of objects/variables
        String baseUrl = "https://www.policybazaar.com/";
        System.setProperty("webdriver.chrome.driver","../../../../WebDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        NavigationCommands.gotoURL(driver, baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}