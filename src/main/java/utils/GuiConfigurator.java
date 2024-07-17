package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GuiConfigurator {

    private JFrame frame;
    private JTextField phraseField;
    private JTextField locationField;
    private JTextField elementsField;
    private JTextField priceFromField;
    private JTextField priceToField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private final Consumer<SearchConfig> onRun;
    private JPanel inputPanel;
    private JScrollPane scrollPane;

    public GuiConfigurator(Consumer<SearchConfig> onRun) {
        this.onRun = onRun;
        initialize();
    }

    public void display() {
        EventQueue.invokeLater(() -> frame.setVisible(true));
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Searching phrase:"));
        phraseField = new JTextField(10);
        inputPanel.add(phraseField);

        inputPanel.add(new JLabel("Search location:"));
        locationField = new JTextField(10);
        inputPanel.add(locationField);

        inputPanel.add(new JLabel("Products to save in Excel:"));
        elementsField = new JTextField("15", 10);
        inputPanel.add(elementsField);

        inputPanel.add(new JLabel("Starting Price:"));
        priceFromField = new JTextField("10", 10);
        inputPanel.add(priceFromField);

        inputPanel.add(new JLabel("Maximum Price:"));
        priceToField = new JTextField("10000", 10);
        inputPanel.add(priceToField);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);

        JButton runButton = new JButton("Save findings to Excel");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runProgram();
                displayExcelInTable("OLX_Search_Result_2024-07-17.xlsx");
            }
        });

        String[] columnNames = {"Price", "Title", "Link", "Date", "City"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(resultsTable);
        scrollPane.setVisible(false);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(runButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void runProgram() {
        String searchPhrase = phraseField.getText();
        String searchLocation = locationField.getText();
        int saveElements = Integer.parseInt(elementsField.getText());
        int priceFrom = Integer.parseInt(priceFromField.getText());
        int priceTo = Integer.parseInt(priceToField.getText());

        SearchConfig config = new SearchConfig(searchPhrase, searchLocation, saveElements, priceFrom, priceTo);
        onRun.accept(config);
    }

    private void displayExcelInTable(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            tableModel.setRowCount(0); // Clear existing data

            for (Row row : sheet) {
                List<Object> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue());
                            } else {
                                rowData.add(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            rowData.add(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            rowData.add(cell.getCellFormula());
                            break;
                        default:
                            rowData.add("");
                    }
                }
                tableModel.addRow(rowData.toArray());
            }
            scrollPane.setVisible(true); // Show the table once data is available
            frame.pack(); // Adjust the window size to fit the table

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
