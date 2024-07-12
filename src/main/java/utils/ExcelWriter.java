package utils;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class ExcelWriter{
    private String[] columns = {"Price", "Title", "Link", "Date", "City"};
    private Workbook workbook = new XSSFWorkbook();
    private CreationHelper createHelper = workbook.getCreationHelper();
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
        Collections.sort(searchItemObjectList, (o1, o2) -> {
            LocalDate date1 = LocalDate.parse(o1.getDate());
            LocalDate date2 = LocalDate.parse(o2.getDate());
            return date2.compareTo(date1);
        });

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
        CellStyle priceCellStyle = createPriceCellStyle();
        CellStyle dateCellStyle = createDateCellStyle();
        CellStyle hyperlinkCellStyle = createHyperlinkCellStyle();
        int rowNum = 1;

        for (SearchItemObject itemObject : searchItemObjectList) {
            Row row = sheet.createRow(rowNum++);

            // Set price value
            Cell priceCell = row.createCell(0);
            priceCell.setCellValue(itemObject.getPrice());
            priceCell.setCellStyle(priceCellStyle);

            // Set title
            row.createCell(1).setCellValue(itemObject.getTitle());

            // Create and set the hyperlink
            Cell linkCell = row.createCell(2);
            linkCell.setCellValue(itemObject.getLink());
            Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress(itemObject.getLink());
            linkCell.setHyperlink(hyperlink);
            linkCell.setCellStyle(hyperlinkCellStyle);

            // Parse and set the date
            Cell dateCell = row.createCell(3);
            LocalDate localDate = LocalDate.parse(itemObject.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            dateCell.setCellValue(localDate);
            dateCell.setCellStyle(dateCellStyle);

            // Set city
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

    private CellStyle createPriceCellStyle() {
        CellStyle priceCellStyle = workbook.createCellStyle();
        priceCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
        return priceCellStyle;
    }

    private CellStyle createDateCellStyle() {
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        return dateCellStyle;
    }

    private CellStyle createHyperlinkCellStyle() {
        CellStyle hyperlinkCellStyle = workbook.createCellStyle();
        Font hyperlinkFont = workbook.createFont();
        hyperlinkFont.setUnderline(Font.U_SINGLE);
        hyperlinkFont.setColor(IndexedColors.BLUE.getIndex());
        hyperlinkCellStyle.setFont(hyperlinkFont);
        return hyperlinkCellStyle;
    }
}

