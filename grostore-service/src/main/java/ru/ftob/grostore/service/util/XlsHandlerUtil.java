package ru.ftob.grostore.service.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ru.ftob.grostore.model.product.ProductImportFieldType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class XlsHandlerUtil {
    private XlsHandlerUtil() {
    }

    public static HSSFWorkbook getWorkBook(Path file) throws IOException {
        return new HSSFWorkbook(new FileInputStream(file.toFile()));
    }

    public static void saveWorkBook(HSSFWorkbook workbook, Path file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file.toFile());
        workbook.write(fos);
        fos.close();

    }

    public static List<List<String>> getRaw(Path file) throws IOException {
        HSSFSheet sheet = getWorkBook(file).getSheetAt(0);
        List<List<String>> raw = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rawRow = new ArrayList<>();
            for (int i = 0; i < row.getLastCellNum(); i++) {
                rawRow.add(String.format("%8s", row.getCell(i)));
            }
            raw.add(rawRow);
        }
        return raw;
    }

    public static boolean addFieldsToHeader(Path file, List<ProductImportFieldType> fields) throws IOException {
        HSSFWorkbook workbook = getWorkBook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);

        if ("#config".equals(sheet.getRow(0).getCell(0).getStringCellValue())) {
            sheet.shiftRows(2, sheet.getLastRowNum(), -2);
        }
        sheet.shiftRows(0, sheet.getLastRowNum(), 2);
        sheet.getRow(0).createCell(0).setCellValue("#config");
        sheet.getRow(0).createCell(1).setCellValue(LocalDateTime.now().toString());
        Row header = sheet.getRow(1);

        for (int i = 0; i < fields.size(); i++) {
            if (null == header.getCell(i)) {
                header.createCell(i);
            }
            if(null == fields.get(i)) {
                header.getCell(i).setCellValue("");
            } else {
                header.getCell(i).setCellValue(fields.get(i).toString());
            }
        }

        saveWorkBook(workbook, file);
        return true;
    }
}
