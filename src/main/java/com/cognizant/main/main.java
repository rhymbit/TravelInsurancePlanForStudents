package com.cognizant.main;

import com.cognizant.CarInsurance.CarInsurancePage2;
import com.cognizant.CarInsurance.ReadExcelCar;
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
        // give sheet index as 1 to access CarInsurance data
        ReadExcelCar ex = new ReadExcelCar(Configuration.getProperty("excelFilePath"), 1);
        Map<String, Map<String, String>> excelData =ex.getExcelAsMap();
        System.out.println("Excel Data for country : "+excelData.get("1").get("place")); //this line is just to check whether class is working correctly or not
        //fetch complete dictionary using this object "excelData"
        System.out.println("excelData as Map: "+excelData);


    }
}
