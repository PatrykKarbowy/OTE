package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter{
    private String[] columns = {"Price", "Title", "Link", "Date", "City"};
    private Workbook workbook = new XSSFWorkbook();
    private Sheet sheet;
    private short headerFontHeight;
    private boolean isBold;

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public short getHeaderFontHeight() {
        return headerFontHeight;
    }

    public void setHeaderFontHeight(short headerFontHeight) {
        this.headerFontHeight = headerFontHeight;
    }

    public ExcelWriter(short headerFontHeight, boolean isBold){
        this.headerFontHeight = headerFontHeight;
        this.isBold = isBold;
    }

    public void saveSearchResultToExcelFile(String fileName, String sheetName, List<SearchItemObject> searchItemObjectList){
        createEmptySheet(sheetName);
        fillExcelWithProductData(searchItemObjectList);

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(fileName + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEmptySheet(String sheetName) {
        sheet = workbook.createSheet(sheetName);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont());
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void fillExcelWithProductData(List<SearchItemObject> searchItemObjectList){
        int rowNum = 1;
        for (SearchItemObject itemObject : searchItemObjectList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(itemObject.getPrice());
            row.createCell(1).setCellValue(itemObject.getTitle());
            row.createCell(2).setCellValue(itemObject.getLink());
            row.createCell(3).setCellValue(itemObject.getDate());
            row.createCell(4).setCellValue(itemObject.getCity());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private Font headerFont(){
        Font headerFont = workbook.createFont();
        headerFont.setBold(isBold);
        headerFont.setFontHeightInPoints(headerFontHeight);
        return headerFont;
    }
}

