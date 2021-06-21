package com.cognizant.CarInsurance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class ReadExcelCar {
    //declaring class level variables and constructor to set filepath and sheetIndex
    private String filePath;
    private int sheetIndex;
    public ReadExcelCar(String filePath, int sheetIndex){
        this.filePath=filePath;
        this.sheetIndex=sheetIndex;
    }
    //this method returns desired sheet based on sheet index
    private XSSFSheet getSheet() throws IOException {
        FileInputStream readFile= new FileInputStream(filePath);
        XSSFWorkbook workbook=new XSSFWorkbook(readFile);
        XSSFSheet sheet=workbook.getSheetAt(sheetIndex);
        return sheet;
    }
    //this methods reads Excel data in map
    public Map<String, Map<String, String>> getExcelAsMap() throws IOException {
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
        //System.out.println(rowCount);
        int columnCount = 7;
        //System.out.println(columnCount);
        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> singleRowData = new HashMap<String, String>();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
                singleRowData.put(columnHeader.get(j), cell.toString());
            }
            completeSheetData.put(String.valueOf(i), singleRowData);
        }
        return completeSheetData;
    }



}
