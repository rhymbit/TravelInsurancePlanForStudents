package com.cognizant.inputdata;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingExcel {
    public String readExcel(int row, int col, String filePath, String sheetName) throws IOException{
        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFCell cell = sheet.getRow(row).getCell(col);
        return String.valueOf(cell);

    }


}
