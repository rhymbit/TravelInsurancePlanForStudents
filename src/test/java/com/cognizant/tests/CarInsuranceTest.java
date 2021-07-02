package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcelCar;
import com.cognizant.carinsurance.CarInsurancePO;
import com.cognizant.carinsurance.CarInsurancePage2;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.utilities.BrowserUtils;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.Global_VARS;
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

public class CarInsuranceTest extends TestBase {
    private WebDriver driver;
    private String browserName;
    private String screenShotPath;
    protected Map<String,String> carData = null;

    /**
     * Method to set the screenshot path for TravelInsurance test.
     * @param pngFileName Screenshot file name, like, {@code carData.png}.
     */
    private void setScreenShotPath(String pngFileName) {
        this.screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
                "carInsurance", browserName, pngFileName)
                .toString();
    }
    //local variables
    private String placeKey= "place";
    private String nameKey= "name";
    private String invalid_emailKey="invalid email";
    private String valid_emailKey="valid email";
    private String invalid_phoneKey="invalid phone";
    private String valid_phoneKey="valid phone";


    //private ReadExcelCar ex = new ReadExcelCar(Configuration.getProperty("excelFilePath"), 1);

    /**
     * Method to simulate car insurance tests on policybazaar.com
     */
    @Test(enabled = true)
    public void testCarInsuranceCase() throws IOException{
        try {
            // get homepage instance
            HomePagePO homepage = new HomePagePO(driver);
            // open homepage url
            homepage.openHomePage();
            BrowserUtils.wait(driver, 10);

            // click on Car Insurance Button
            CarInsurancePO carInsurancePage = homepage.gotoCarInsurance();

            // get fill form instance
            CarInsurancePage2 carInsuranceTest = new CarInsurancePage2(driver);

            //sets City in the form
            carInsuranceTest.setCity(driver, carData.get(placeKey));

            //sets Car Brand in the form
            carInsuranceTest.setCarBrand(driver);

            //sets Model Name in the form
            carInsuranceTest.setModel(driver);

            //sets Type of Vehicle in the form
            carInsuranceTest.setType(driver);

            //sets Variant of Vehicle in the form
            carInsuranceTest.setVariant(driver);

            //sets Car Registration Year in the form
            carInsuranceTest.setCarRegisterYear(driver);

            //sets Name, Email and Phone in the form
            carInsuranceTest.fillDetailsForm(driver,
                    carData.get(nameKey),
                    carData.get(invalid_emailKey),
                    carData.get(invalid_phoneKey));
            //captures Screenshot
            setScreenShotPath("carInsurance1.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            //[TEST CASE 1]fill valid name and email but invalid phone number
            carInsuranceTest.fillDetailsForm(driver, carData.get(nameKey).toString(), carData.get(valid_emailKey).toString(), carData.get(invalid_phoneKey).toString());
            //captures Screenshot
            setScreenShotPath("carInsurance2.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            //[TEST CASE 2]fill valid name and phone, but invalid email
            carInsuranceTest.fillDetailsForm(driver, carData.get(nameKey).toString(), carData.get(invalid_emailKey).toString(), carData.get(valid_phoneKey).toString());
            //captures Screenshot
            setScreenShotPath("carInsurance3.png");
            BrowserUtils.screenshot(driver, screenShotPath);

            //[TEST CASE 3]fill the form with all valid inputs- name, email and phone
            carInsuranceTest.fillDetailsForm(driver, carData.get(nameKey).toString(), carData.get(valid_emailKey).toString(), carData.get(valid_phoneKey).toString());
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
            DriverSetup driverSetup = new DriverSetup();
            driverSetup.setDriver(Global_VARS.DEF_BROWSER,
                    Global_VARS.DEF_PLATFORM,
                    Global_VARS.DEF_ENVIRONMENT);
            driver = driverSetup.getDriver();
        } catch (MalformedURLException e) {
            Reporter.log("Selenium grid's Hub URL is not set properly, or is not working");
        }

        browserName = browser;
        //new refactored
        ReadExcelCar readExcel = new ReadExcelCar(1);
        carData = readExcel.getCarInsuranceData(1);
    }

    /**
     * This method closes the workbook as well as browser windows.
     */
    @AfterClass
    protected void testClassTearDown() {
        //readExcel.closeWorkbook();
        try {
            driver.close();
            driver.quit();
        } catch (WebDriverException e) {
            Reporter.log("Something went wrong with the web driver object when it was being closed.");
        } catch (Exception e) {
            Reporter.log("Something went wrong when closing the web driver object.");
        }
    }
}