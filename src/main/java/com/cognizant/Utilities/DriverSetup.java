package com.cognizant.Utilities;

import java.util.concurrent.TimeUnit;
import com.cognizant.Utilities.NavigationCommands;

import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
    //This setups Firefox Driver.
//    public static WebDriver setupFirefoxDriver()
//    {
//        // declaration and instantiation of objects/variables
//        String baseUrl = "https://www.policybazaar.com/";
//        System.setProperty("webdriver.gecko.driver","../../../../WebDriver/geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        NavigationCommands.gotoURL(driver, baseUrl);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        return driver;
//    }
//
//    //This setups Firefox Driver.
//    public static WebDriver setupChromeDriver()
//    {
//        // declaration and instantiation of objects/variables
//        String baseUrl = "https://www.policybazaar.com/";
//        System.setProperty("webdriver.chrome.driver","../../../../WebDriver/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        NavigationCommands.gotoURL(driver, baseUrl);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        return driver;
//    }

    private static DriverSetup instance = null;
    private static WebDriver driver;

    private DriverSetup() { }

    public static DriverSetup getInstance() {
        if (instance == null) {
            instance = new DriverSetup();
        }
        return instance;
    }

    // set driver instance for a particular browser
    public final void setDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.firefox.driver",
                        Configuration.getProperty("firefoxDriverPath"));
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        Configuration.getProperty("chromeDriverPath"));
            case "edge":
                System.setProperty("webdriver.edge.driver",
                        Configuration.getProperty("edgeDriverPath"));
                driver = new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver",
                        Configuration.getProperty("edgeDriverPath"));
        }
    }

    // set driver instance forcefully to the driver parameter passed to the method
    public final void setDriver(WebDriver _driver) {
        driver = _driver;
    }

    // returns the webdriver instance for the `browser` parameter
    public final WebDriver getDriver(String browser) {
        setDriver(browser);
        return driver;
    }

    // returns the current webdriver instance parameter
    public final WebDriver getCurrentDriver() {
        return driver;
    }
}