package ru.ftob.grostore.service.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ftob.grostore.model.product.ProductImportFieldType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class XlsHandlerUtil {
    private XlsHandlerUtil() {
    }

    public static Workbook getWorkBook(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.indexOf("."));
        if (extension.equals(".xls")) {
            return new HSSFWorkbook(new FileInputStream(file));
        }
        if(extension.equals(".xlsx")) {
            return new XSSFWorkbook(new FileInputStream(file));
        }
        throw new NotOfficeXmlFileException("Unsupported file extension in file: " + fileName + ". Allowed: xls, xlsx.");
    }

    public static void saveWorkBook(Workbook workbook, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

    }

    public static List<List<String>> getRaw(File file) throws IOException {
        Sheet sheet = getWorkBook(file).getSheetAt(0);
        List<List<String>> raw = new ArrayList<>();
        int rowLimit = 50;
        int startRow = 0;
        if(isFileConfigured(file)) {
            startRow = 2;
        }
        for (Row row : sheet) {
            if(startRow > 0) {
                startRow--;
                continue;
            }
            List<String> rawRow = new ArrayList<>();

            for (int i = 0; i < row.getLastCellNum(); i++) {
                rawRow.add(String.format("%8s", row.getCell(i)));
            }
            raw.add(rawRow);
            if (--rowLimit == 0) {
                break;
            }
        }
        return raw;
    }

    public static boolean isFileConfigured(File file) throws IOException {
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet.getRow(1) != null &&
                sheet.getRow(1).getCell(1) != null &&
                sheet.getRow(1).getCell(1).getCellTypeEnum().equals(CellType.STRING) &&
                "#config".equals(sheet.getRow(1).getCell(1).getStringCellValue())) {
            return true;
        }
        return false;
    }

    public static void addFieldsToHeader(File file, List<ProductImportFieldType> fields) throws IOException {
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);

        if(!isFileConfigured(file)) {
            sheet.shiftRows(0, sheet.getLastRowNum(), 2);
            sheet.createRow(0);
            sheet.createRow(1);
        }

        sheet.getRow(1).createCell(0).setCellValue("====");
        sheet.getRow(1).createCell(1).setCellValue("#config");
        sheet.getRow(1).createCell(2).setCellValue(LocalDateTime.now().toString());
        sheet.getRow(1).createCell(3).setCellValue("====");
        Row header = sheet.getRow(0);

        for (int i = 0; i < fields.size(); i++) {
            if (null == header.getCell(i)) {
                header.createCell(i);
            }
            if (null == fields.get(i)) {
                header.getCell(i).setCellValue("");
            } else {
                header.getCell(i).setCellValue(fields.get(i).toString());
            }
        }

        saveWorkBook(workbook, file);
    }
}
