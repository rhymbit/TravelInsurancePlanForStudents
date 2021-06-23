//package com.cognizant.tests;
//
//import com.cognizant.apachePOI.ReadExcelCar;
//import com.cognizant.configuration.Configuration;
//import com.cognizant.homepage.HomePagePO;
//import com.cognizant.carinsurance.CarInsurancePO;
//import com.cognizant.carinsurance.CarInsurancePage2;
//import com.cognizant.utilities.DriverSetup;
//import com.cognizant.utilities.Navigate;
//import org.testng.Reporter;
//import org.testng.annotations.*;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Map;
//
//public class CarInsuranceTest extends TestBase {
//    private String browserName;
//    private String screenShotPath;
//
//    private void setScreenShotPath(String pngFileName) {
//        this.screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
//                "carInsurance", browserName, pngFileName)
//                .toString();
//    }
//
//    private ReadExcelCar ex = new ReadExcelCar(Configuration.getProperty("excelFilePath"), 1);
//
//    @Test
//    public void testCarInsuranceCase() throws IOException{
//        Map<String, Map<String, String>> excelData =ex.getExcelAsMap();
////        int index = 1;
////        String screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
////                "carInsurance", "carInsurance" + index + ".png")
////                .toString();
//        // get homepage instance
//        HomePagePO homepage = new HomePagePO(driver);
//        // open homepage url
//        homepage.openHomePage();
//        Navigate.wait(driver,10);
//
//        // click on Car Insurance Button
//        CarInsurancePO carInsurancePage = homepage.gotoCarInsurance();
//
//        //click on Proceed without Car Number button
//        //driver = carInsurancePage.clickonProceed();
//
//        // get fill form instance
//        CarInsurancePage2 carinsurancetest = new CarInsurancePage2(driver);
//
//        //sets City in the form
//        carinsurancetest.setCity(driver, excelData.get("1").get("place").toString());
//
//        //sets Car Brand in the form
//        carinsurancetest.setCarBrand(driver);
//
//        //sets Model Name in the form
//        carinsurancetest.setModel(driver);
//
//        //sets Type of Vehicle in the form
//        carinsurancetest.setType(driver);
//
//        //sets Variant of Vehicle in the form
//        carinsurancetest.setVariant(driver);
//
//        //sets Car Registration Year in the form
//        carinsurancetest.setCarRegisterYear(driver);
//
//        //sets Name, Email and Phone in the form
//        carinsurancetest.fillDetailsForm(driver,
//                excelData.get("1").get("name").toString(),
//                excelData.get("1").get("invalid email").toString(),
//                excelData.get("1").get("invalid phone").toString());
//        //captures Screenshot
//        setScreenShotPath("carInsurance1.png");
//        Navigate.screenshot(driver, screenShotPath);
////        index = index + 1;
////        screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
////                "carInsurance", "carInsurance" + index + ".png")
////                .toString();
//
//        carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("valid email").toString(), excelData.get("1").get("invalid phone").toString());
//        //captures Screenshot
//        setScreenShotPath("carInsurance2.png");
//        Navigate.screenshot(driver, screenShotPath);
////        index = index + 1;
////        screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
////                "carInsurance", "carInsurance" + index + ".png")
////                .toString();
//
//        carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("invalid email").toString(), excelData.get("1").get("valid phone").toString());
//        //captures Screenshot
//        setScreenShotPath("carInsurance3.png");
//        Navigate.screenshot(driver, screenShotPath);
////        index = index + 1;
////        screenShotPath = Path.of(Configuration.getProperty("screenshotPath"),
////                "carInsurance", "carInsurance" + index + ".png")
////                .toString();
//
//        carinsurancetest.fillDetailsForm(driver, excelData.get("1").get("name").toString(), excelData.get("1").get("valid email").toString(), excelData.get("1").get("valid phone").toString());
//        //captures Screenshot
//        setScreenShotPath("carInsurance4.png");
//        Navigate.screenshot(driver, screenShotPath);
//    }
//
//    @Override
//    @BeforeClass
//    @Parameters("browser")
//    protected void testClassSetup(String browser) {
//        DriverSetup instance = DriverSetup.getInstance();
//        if (browser.equalsIgnoreCase("edge")){
//            driver = instance.getDriver("edge");
//            browserName = "edge";
//        }
//        else if (browser.equalsIgnoreCase("chrome")) {
//            driver = instance.getDriver("chrome");
//            browserName = "chrome";
//        }
//        else if (browser.equalsIgnoreCase("firefox")) {
//            driver = instance.getDriver("firefox");
//            browserName = "firefox";
//        }
//        else {
//            Reporter.log("Install one of the following browsers to run this project:-");
//            Reporter.log("Microsoft Edge");
//            Reporter.log("Google Chrome");
//            Reporter.log("Firefox");
//            System.exit(1);
//        }
//    }
//
//    @Override
//    @AfterClass
//    protected void testClassTearDown() {
//        Navigate.closeDriver(driver);
//    }
//
//    @Override
//    protected void testMethodsSetup() { }
//
//    @Override
//    @AfterTest
//    protected void testMethodsTearDown() { }
//}