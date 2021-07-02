package com.cognizant.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * This class contains various methods used by WebDriver instance
 * to work with the web browser.
 */
public class BrowserUtils {

    /**
     * Method to navigate to previous page in the browser.
     * @param driver Current {@code WebDriver} instance.
     */
    public static void navigateBack(WebDriver driver)
    {
        driver.navigate().back();
    }

    /**
     * Method to navigate to forward page in the browser.
     * @param driver Current {@code WebDriver} instance.
     */
    public static void navigateForward(WebDriver driver)
    {
        driver.navigate().forward();
    }

    /**
     * Method to refresh the current page in the browser.
     * @param driver Current {@code WebDriver} instance.
     */
    public static void refresh(WebDriver driver)
    {
        driver.navigate().refresh();
    }

    /**
     * Method to navigate to a given URL
     * @param driver Current {@code WebDriver} instance.
     * @param url URL {@code String} to open in the browser instance.
     * @return Current {@code WebDriver} instance.
     */
    public static WebDriver gotoUrl(WebDriver driver, String url)
    {
        driver.get(url);
        return driver;
    }

    /**
     * Method to get the current WebPage title.
     * @param driver Current {@code WebDriver} instance.
     * @return Current webpage {@code String} title.
     */
    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * Method to click on a web page element.
     * @param elem {@code WebElement} type to click on
     */
    public static void clickElement(WebElement elem) {
        elem.click();
    }

    // implicit wait to load the page contents

    /**
     * Implicit wait method. Can be used to wait for page's contents to load.
     * @param driver Current {@code WebDriver} instance.
     * @param seconds Time in seconds for implicitly waiting on the webpage.
     */
    public static void wait(WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Explicit wait for web elements. This method can be used to explicitly wait for a web
     * element to be present on the current webpage. By default this method waits for
     * 10 seconds for an element to be present in the DOM, after which it'll throw
     * NoSuchElementException and TimeOutException.
     * @param driver Current {@code WebDriver} instance.
     * @param locator {@code org.openqa.selenium.By} locator for the web element.
     * @throws org.openqa.selenium.NoSuchElementException
     * @throws org.openqa.selenium.TimeoutException
     */
    public static void waitForElement(WebDriver driver, By locator)
            throws org.openqa.selenium.NoSuchElementException,
            org.openqa.selenium.TimeoutException
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Fluent Wait for web elements. This method can be used to fluently wait for a web
     * element to be present on the current webpage. By default this methods waits for
     * 10 seconds, after which it'll throw NoSuchElementException and TimeOutException.
     * @param driver Current {@code WebDriver} instance.
     * @param locator {@code org.openqa.selenium.By} locator for the web element.
     * @throws org.openqa.selenium.NoSuchElementException
     * @throws org.openqa.selenium.TimeoutException
     */
    public static void waitForElementFluent(WebDriver driver, By locator)
            throws org.openqa.selenium.NoSuchElementException,
            org.openqa.selenium.TimeoutException
    {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        WebElement elem = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    /**
     * This method can be used to select items in a drop-down-list on a webpage.
     * The second parameter is the {@code String} value's name, that you want to select from the drop-down.
     * @param elem Drop-down-list's {@code WebElement} object.
     * @param value {@code String} value that you want to select from the drop-down-menu.
     */
    public static void selectByValue(WebElement elem, String value) {
        Select select = new Select(elem);
        select.selectByValue(value);
    }

    /**
     * This method can be used to select items in a drop-down-list on a webpage.
     * This second parameter is the {@code Integer} index, that you want to select from the drop-down.
     * @param elem rop-down-list's {@code WebElement} object.
     * @param index {@code Integer} index of the item you want to select from the drop-down-menu.
     */
    public static void selectByIndex(WebElement elem, int index) {
        Select select = new Select(elem);
        select.selectByIndex(index);
    }

    /**
     * This method simulates the pressing of {@code Enter} key on the keyboard
     * @param driver Current {@code WebDriver} instance.
     */
    public static void actionPressEnter(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();
    }

    /**
     * This method is used to simulate scrolling on the current webpage.
     * @param driver Current {@code WebDriver} instance.
     * @param scrollingUnit {@code Integer} units to scroll on the page.
     */
    public static void scrollWindow(WebDriver driver, int scrollingUnit) {
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        jse.executeScript("window.scrollBy(0,"+ scrollingUnit+ ")");
    }

    /**
     * This method is used to take the screenshots on the current webpage.
     * @param driver Current {@code WebDriver} instance.
     * @param filePath Screenshot file save location in {@code String}
     * @throws IOException
     */
    public static void screenshot(WebDriver driver, String filePath) throws IOException{
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.deleteIfExists(Path.of(filePath));
        Files.copy(srcFile.toPath(), Path.of(filePath));
    }

    /**
     * Returns the currently simulating browser's {@code String} name.
      * @param driver Current {@code WebDriver} instance.
     * @return {@code String} name of the currently automating browser.
     */
    public static String getBrowserName(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        return cap.getBrowserName();
    }

    /**
     * This method is used to enter {@code String} values in text boxes of the browser.
     * @param elem TextBox {@code WebElement} object.
     * @param value {@code String} text value to enter in the text box.
     */
    public static void sendKeys(WebElement elem, String value) {
        elem.clear();
        elem.sendKeys(value);
    }

    /**
     * This method is used to clear the text inside a text box in a web page.
     * @param elem TextBox {@code WebElement} object.
     */
    public static void clearKeys(WebElement elem) {
        elem.clear();
    }
}
