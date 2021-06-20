package com.cognizant.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
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

    public static void waitForElement(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementFluent(WebDriver driver, By locator) {
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

    public static void selectByValue(WebElement elem, String value) {
        Select select = new Select(elem);
        select.selectByValue(value);
    }

    public static void selectByIndex(WebElement elem, int index) {
        Select select = new Select(elem);
        select.selectByIndex(index);
    }

    public static void actionPressEnter(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();
    }

    public static void scrollWindow(WebDriver driver, int scrollingUnit) {
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        jse.executeScript("window.scrollBy(0,"+ scrollingUnit+ ")");
    }

    public static void screenshot(WebDriver driver, String filePath) {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.deleteIfExists(Path.of(filePath));
            Files.copy(srcFile.toPath(), Path.of(filePath));
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public static String getBrowserName(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        return cap.getBrowserName();
    }

    //Closing the Browser
    public static WebDriver closeDriver(WebDriver driver)
    {
        driver.close();
        driver.quit();
        return driver;
    }

    public static void sendKeys(WebElement elem, String value) {
        elem.sendKeys(value);
    }
}
