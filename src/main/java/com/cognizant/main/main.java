package com.cognizant.main;

import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.apachePOI.ReadExcelCar;
import com.cognizant.configuration.Configuration;

import java.io.IOException;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {
        Configuration.createConfigurations();
        ReadExcel excel = new ReadExcel(1);
        Map<String,String> map = excel.getTravelInsuranceData(0);
        double age = Double.parseDouble(map.get("phoneNumber"));
        System.out.println((int)age);
        System.out.println(map.get("phoneNumber"));

    }
}
