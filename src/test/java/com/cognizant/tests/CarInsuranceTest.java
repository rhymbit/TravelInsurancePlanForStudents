package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcelCar;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.carinsurance.CarInsurancePO;
import com.cognizant.carinsurance.CarInsurancePage2;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
import com.cognizant.utilities.BrowserUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Map;
    /**
     * This class contains methods and functionalities to simulate
     * Car Insurance requirement for the project.
     */
public class CarInsuranceTest extends TestBase {
    private WebDriver driver;
    private String browserName;
    private String screenShotPath;

    /**
     * Method to set the screenshot path for TravelInsurance test.
     * @param pngFileName Screenshot file name, like, {@code travelData.png}.
     */
    private void setScreenShotPath(String pngFileName) {
        this.screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
                "carInsurance", browserName, pngFileName)
                .toString();
    }

    private ReadExcelCar ex = new ReadExcelCar(Configuration.getProperty("excelFilePath"), 1);

    /**
    * Method to simulate travel insurance tests on policybazar.com
    */
    @Test(enabled = false)
    public void testCarInsuranceCase() throws IOException {
        try {
            Map<String, Map<String, String>> excelData = ex.getExcelAsMap();
            HomePagePO homepage = new HomePagePO(driver);
            homepage.openHomePage();
            BrowserUtils.wait(driver, 10);

            // click on Car Insurance Button
            CarInsurancePO carInsurancePage = homepage.gotoCarInsurance();

            // get fill form instance
            CarInsurancePage2 carinsurancetest = new CarInsurancePage2(driver);

            //sets City in the form
            carinsurancetest.setCity(driver, excelData.get("1").get("place").toString());

            //sets Car Brand in the form
            carinsurancetest.setCarBrand(driver);

            //sets Model Name in the form
            carinsurancetest.setModel(driver);

            //sets Type of Vehicle in the form
            carinsurancetest.setType(driver);

            //sets Variant of Vehicle in the form
            carinsurancetest.setVariant(driver);

            //sets Car Registration Year in the form
            carinsurancetest.setCarRegisterYear(driver);

            //sets Name, Email and Phone in the form
            carinsurancetest.fillDetailsForm(driver,
                    excelData.get("1").get("name").toString(),
                    excelData.get("1").get("invalid email").toString(),
                    excelData.get("1").get("invalid phone").toString());
            //captures Screenshot
            setScreenShotPath("carInsurance1.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("valid email").toString(), excelData.get("1").get("invalid phone").toString());
            //captures Screenshot
            setScreenShotPath("carInsurance2.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("invalid email").toString(), excelData.get("1").get("valid phone").toString());
            setScreenShotPath("carInsurance3.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("valid email").toString(), excelData.get("1").get("valid phone").toString());
            //captures Screenshot
            setScreenShotPath("carInsurance4.png");
            BrowserUtils.screenshot(driver, screenShotPath);
        } catch (IOException e) {
            Reporter.log("Screenshot file path is not set correctly");
            throw new SkipException("Screenshot file path is not set correctly");
        } catch (TimeoutException e) {
            Reporter.log("WebDriver timeout. Failed to locate a web element.");
            throw new SkipException("WebDriver timeout. Failed to locate a web element.");
        } catch (NoSuchElementException e) {
            Reporter.log("Failed to locate a WebElement object.");
            throw new SkipException("Failed to locate a WebElement object");
        }
    }

        /**
         * This method gets simulation parameters from {@code testng.xml} and passes
         * them to {@link com.cognizant.utilities.DriverSetup} class, which returns a
         * {@code WebDriver} object for automating browser.
         * @param browser Browser name for which driver instance is set for. Currently supported
         *                browsers {@code chrome}, {@code edge}, {@code firefox}.
         * @param platform Operating system, on which automation would run.
         * @param environment   Takes either of two values - {@code local} for running automation locally and
         *                      {@code remote} for running instance using Selenium Grid.
         */
    @BeforeClass
    @Parameters({"browser", "platform", "environment"})
    protected void testClassSetup(@Optional(Global_VARS.BROWSER) String browser,
                                  @Optional(Global_VARS.PLATFORM) String platform,
                                  @Optional(Global_VARS.ENVIRONMENT) String environment) {

        Global_VARS.DEF_BROWSER = System.getProperty("browser", browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform", platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment", environment);
        try {
            DriverSetup.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                    Global_VARS.DEF_PLATFORM,
                    Global_VARS.DEF_ENVIRONMENT);
        } catch (MalformedURLException e) {
            Reporter.log("Selenium grid's Hub URL is not set properly, or is not working");
        }
        driver = DriverSetup.getInstance().getDriver();
        browserName = browser;
    }

    @Override
    @AfterClass
    protected void testClassTearDown() {
        try {
            DriverSetup.getInstance().closeDriver(driver);
        } catch (WebDriverException e) {
            Reporter.log("Something went wrong with the web driver object when it was being closed.");
        } catch (Exception e) {
            Reporter.log("Something went wrong when closing the web driver object.");
        }
    }

    @Override
    protected void testMethodsSetup() { }

    @Override
    @AfterTest
    protected void testMethodsTearDown() { }
}