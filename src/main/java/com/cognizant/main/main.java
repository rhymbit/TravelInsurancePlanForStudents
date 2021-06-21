package com.cognizant.main;

import com.cognizant.CarInsurance.CarInsurancePage2;
import com.cognizant.Utilities.DriverSetup;
import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import com.cognizant.homepage.HomePagePO;
import com.cognizant.travelinsurance.TravelInsurancePO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CancellationException;

public class main {
    public static void main(String[] args) throws IOException {
        Configuration.createConfigurations();
        ReadExcel ex = new ReadExcel(Configuration.getProperty("excelFilePath"), 0);
        Map<String, Map<String, String>> excelData =ex.getExcelAsMap();
        System.out.println("Excel Data for country : "+excelData.get("1").get("Country"));
        System.out.println("excelData as Map: "+excelData);


    }
}
