package com.cognizant.utilities;

import com.cognizant.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * This class is used to create and manage the WebDriver instance,
 * which is used to perform test automations.
 */
public class DriverSetup {
    // local variables
    private static DriverSetup instance = null;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<>();
    private String getEnv;

    // Private constructor for singleton class
    private DriverSetup() {
    }

    /**
     * This method returns a singleton instance of the DriverSetup type.
     *
     * @return DriverSetup
     */
    public static DriverSetup getInstance() {
        if (instance == null) {
            instance = new DriverSetup();
        }
        return instance;
    }

    /**
     * Used to set the WebDriver instance to be used for automation. Set's the
     * driver instance for both {@code local} and {@code remote} or selenium grid
     * automations.
     *
     * @param browser       Browser name for which driver instance is set for. Currently supported
     *                      browsers {@code chrome}, {@code edge}, {@code firefox}.
     * @param platform      Operating system, on which automation would run.
     * @param environment   Takes either of two values - {@code local} for running automation locally and
     *                      {@code remote} for running instance using Selenium Grid.
     * @param optPreference Optional browser configuration parameters.
     * @throws WebDriverException If a browser is not installed on the system.
     */
    public final void setDriver(String browser,// chrome, edge, firefox
                                String platform,// linux, win, mac
                                String environment,// local, remote, sauce labs
                                Map<String, Object>... optPreference)
            throws WebDriverException, // Thrown when a browser is not installed
            MalformedURLException // Thrown to indicate that a malformed URL has occurred.
    {
        // browser configurations are set using this type
        DesiredCapabilities caps = null;

        // localhost URL for selenium grid
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
                // setup the browser preference and capabilities
                caps = DesiredCapabilities.chrome();

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
                    WebDriver edgeDriver = new EdgeDriver();
                    driver.set(edgeDriver);
                }
                break;
        }

        // set session variables
        getEnv = environment;
        sessionPlatform.set(platform);
        sessionBrowser.set(caps.getBrowserName());
        sessionVersion.set(caps.getVersion());

        if (environment.equalsIgnoreCase("remote")) {
            RemoteWebDriver _driver = new RemoteWebDriver(new URL(remoteHubURL), caps);
            driver.set(_driver);
        }

        // maximize the driver window for taking screenshots
        getDriver().manage().window().maximize();
    }

    /**
     * Returns the current WebDriver instance
     * @return Current {@code WebDriver} instance
     */
    public WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Utility method to close the
     * @param driver WebDriver instance to close the driver
     */
    public void closeDriver(WebDriver driver) throws WebDriverException, Exception {
        driver.quit();
    }

    /**
     * This method returns the current browser session.
     * @return {@code String} value of the current browser session.
     */
    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    /**
     * This method returns the current session version.
     * @return {@code String} value of the current session version.
     */
    public String getSessionVersion() {
        return sessionVersion.get();
    }

    /**
     * This method returns the current session platform.
     * @return {@code String} value of the current session platform
     */
    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

    /**
     * This method returns the current os execution environment name.
     * @return {@code String} of the current execution environment name.
     */
    public String getEnvironment() {
        return getEnv;
    }
}