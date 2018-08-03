package ru.ftob.grostore.handler;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XlsHandler {

    private MultipartFile file;

    private HSSFWorkbook workbook;

    public XlsHandler(MultipartFile file) {
        this.file = file;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> getRaw() {
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<List<String>> raw = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> rawRow = new ArrayList<>();
            while (cellIterator.hasNext()) {
                //TODO if CELL is empty
                Cell cell = cellIterator.next();
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        rawRow.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        rawRow.add(String.valueOf(cell.getNumericCellValue()));
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        rawRow.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    default:
                        rawRow.add("");
                        break;
                }
            }
            raw.add(rawRow);
        }
        return raw;
    }
}
