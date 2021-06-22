package com.cognizant.apachePOI;

import com.cognizant.configuration.Configuration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public class ReadExcel {
    private Path filePath;
    private XSSFWorkbook workbook = null;
    private int sheetIndex;
    private DataFormatter formatter = null;

    public ReadExcel(int sheetIndex){
        this.filePath = Path.of(Configuration.getProperty("excelFilePath"));
        this.sheetIndex=sheetIndex;
        readExcelFile();
        formatter = new DataFormatter();
    }

    private void readExcelFile() {
        try (InputStream in = Files.newInputStream(filePath)) {
            workbook = new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
    //this method returns desired sheet based on sheet index
    private XSSFSheet getSheet() {
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet;
    }

    //this methods reads Excel data in map
    public Map<String, Map<String, String>> getExcelAsMap() {
        XSSFSheet sheet= getSheet();
        //creating nested hashmap for fetching excel data
        Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
        // storing column names in a ArrayList
        List<String> columnHeader = new ArrayList<String>();
        Row row = sheet.getRow(0); // creating row object for header row
        //iterate over header row and store values in ArrayList
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            columnHeader.add(cellIterator.next().getStringCellValue());
        }
        //nested loop to iterate over each row and each cell
        //int rowCount = row.getLastCellNum();
        int rowCount=1;
        int columnCount = row.getLastCellNum();

        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> singleRowData = new HashMap<String, String>();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
                String text = formatter.formatCellValue(cell);
                singleRowData.put(columnHeader.get(j), text);
            }
            completeSheetData.put(String.valueOf(i), singleRowData);
        }
        return completeSheetData;
    }

    public Map<String,String> getTravelInsuranceData(int sheetIndex) {
        this.sheetIndex = sheetIndex;
        Map<String,String> map =  getExcelAsMap().get("1");
        return map;
    }
}
