package com.cognizant.utilities;

import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
    // local variables
    private static DriverSetup instance = null;
    private static final int IMPLICIT_TIMEOUT = 0;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<>();
    private String getEnv = null;


    private DriverSetup() { }

    /**
     * getInstance method to get active driver instance
     * @return DriverSetup
     */
    public static DriverSetup getInstance() {
        if (instance == null) {
            instance = new DriverSetup();
        }
        return instance;
    }

    public final void setDriver(String browser,// chrome, edge, firefox
                                String platform,// linux, win, mac
                                String environment,// local, remote, sauce labs
                                Map<String, Object>... optPreference)
    {
        // driver preferences are set using this variable
        DesiredCapabilities caps = null;
        String ffVersion = "55.0"; // firefox version
        String remoteHubURL = "http://localhost:4444/wd/hub";

        switch (browser.toLowerCase()) {
            case "firefox":
                // setup the browser preference and capabilities
                caps = DesiredCapabilities.firefox();

                FirefoxOptions ffOpts = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();

                ffProfile.setPreference("browser.autofocus", true);
                ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

                caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
                caps.setCapability("marionette", true);

                if (environment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.gecko.driver",
                            Configuration.getProperty("firefoxDriverPath"));
                    driver.set(new FirefoxDriver(ffOpts.merge(caps)));
                }
                break;

            case "chrome":
                caps = DesiredCapabilities.chrome();

//                ChromeOptions chOptions = new ChromeOptions();
//                Map<String, Object> chromePrefs = new HashMap<String, Object>();
//
//                chromePrefs.put("credentials_enable_service", false);
//
//                chOptions.setExperimentalOption("prefs", chromePrefs);
//                chOptions.addArguments("--disable-plugins", "--disable-extensions",
//                        "--disable-popup-blocking");
//
//                caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
//                caps.setCapability("applicationCacheEnabled", false);

                if (environment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.chrome.driver",
                            Configuration.getProperty("chromeDriverPath"));
                    WebDriver chromeDriver = new ChromeDriver();
                    driver.set(chromeDriver);
                }
                break;

            case "edge":
                // setup the browser preference and capabilities
                caps = DesiredCapabilities.edge();
                if (environment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.edge.driver",
                            Configuration.getProperty("edgeDriverPath"));
                }
                break;
        }

        getEnv = environment;
        sessionPlatform.set(platform);
//        sessionId.set(((RemoteWebDriver) driver.get()).getSessionId().toString());
        sessionBrowser.set(caps.getBrowserName());
        sessionVersion.set(caps.getVersion());

        if (environment.equalsIgnoreCase("remote")) {
//            caps.setCapability("browserName", browser);
//            caps.setCapability("platform", platform);
//            caps.setCapability("applicationName",
//                        platform.toUpperCase() + "-" + browser.toUpperCase());
            try {
                RemoteWebDriver _driver = new RemoteWebDriver(new URL(remoteHubURL), caps);
                driver.set(_driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

//        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();

    }

    // returns the webdriver instance for the `browser` parameter
    public WebDriver getDriver() {
        return driver.get();
    }

    public void closeDriver(WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSessionId() {
        return sessionId.get();
    }

    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    public String getSessionVersion() {
        return sessionVersion.get();
    }

    public String getSessionPlatform() {
        return sessionPlatform.get();
    }
}