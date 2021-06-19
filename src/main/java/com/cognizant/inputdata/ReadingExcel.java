package com.cognizant.inputdata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadingExcel {
    public static Map<String, String> dataMap = new HashMap<String, String>();

    public static Map<String, String> readMapData() throws IOException {

        String path = "src/main/resources/Travel Insurance Data.xlsx";

        FileInputStream fis = new FileInputStream(path);

        Workbook workbook = new XSSFWorkbook(fis);
        // Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();

        //Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();


        // Looping over entire row
        for (int i = 0; i <= lastRow; i++) {

            Row row = sheet.getRow(i);

            // 1st Cell as Value
            Cell valueCell = row.getCell(1);

            // 0th Cell as Key
            Cell keyCell = row.getCell(0);

            //String value = valueCell.getStringCellValue().trim();
            //String key = keyCell.getStringCellValue().trim();

            String key = keyCell.getStringCellValue().trim();
            DataFormatter formatter = new DataFormatter();
            String text = formatter.formatCellValue(valueCell);


            // Putting key & value in dataMap
            dataMap.put(key, text);

            // Putting dataMap to excelFileMap
            //excelFileMap.put("DataSheet", dataMap);
        }

        // Returning excelFileMap
        return dataMap;

    }

    // Method to retrieve value
    public static String getMapData(String key) throws IOException {


        String value = dataMap.get(key);


        return value;

    }
}


