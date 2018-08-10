package ru.ftob.grostore.service.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XlsHandlerUtil {
    private XlsHandlerUtil() {
    }

    public static HSSFWorkbook getWorkBook(Path file) throws IOException {
        return new HSSFWorkbook(new FileInputStream(file.toFile()));
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
}
