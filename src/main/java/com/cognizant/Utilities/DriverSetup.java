package com.cognizant.Utilities;

import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

    private static DriverSetup instance = null;
    private static WebDriver driver=null;

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
                System.setProperty("webdriver.gecko.driver",
                        Configuration.getProperty("firefoxDriverPath"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        Configuration.getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver",
                        Configuration.getProperty("edgeDriverPath"));
                driver = new EdgeDriver();
                break;
        }
    }

    // set driver instance forcefully to the driver parameter passed to the method
    public final void setDriver(WebDriver _driver) {
        this.driver = _driver;
    }

    // returns the webdriver instance for the `browser` parameter
    public final WebDriver getDriver(String browser) {
        this.setDriver(browser);
        return driver;
    }

    // returns the current webdriver instance parameter
    public final WebDriver getCurrentDriver() {
        return driver;
    }
}