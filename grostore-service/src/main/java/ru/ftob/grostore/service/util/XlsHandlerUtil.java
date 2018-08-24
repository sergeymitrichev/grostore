package ru.ftob.grostore.service.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
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
        int rowLimit = 50;
        for (Row row : sheet) {
            List<String> rawRow = new ArrayList<>();

            for (int i = 0; i < row.getLastCellNum(); i++) {
                rawRow.add(String.format("%8s", row.getCell(i)));
            }
            raw.add(rawRow);
            if(--rowLimit == 0) {
                break;
            }
        }
        return raw;
    }

    public static boolean addFieldsToHeader(Path file, List<ProductImportFieldType> fields) throws IOException {
        HSSFWorkbook workbook = getWorkBook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);

        if (sheet.getRow(1) != null &&
                sheet.getRow(1).getCell(1) != null &&
                sheet.getRow(1).getCell(1).getCellTypeEnum().equals(CellType.STRING) &&
                "#config".equals(sheet.getRow(1).getCell(1).getStringCellValue())) {
            sheet.shiftRows(2, sheet.getLastRowNum(), -2);
        }
        sheet.shiftRows(0, sheet.getLastRowNum(), 2);
        sheet.getRow(1).createCell(0).setCellValue("====");
        sheet.getRow(1).createCell(1).setCellValue("#config");
        sheet.getRow(1).createCell(2).setCellValue(LocalDateTime.now().toString());
        sheet.getRow(1).createCell(3).setCellValue("====");
        Row header = sheet.getRow(0);

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
